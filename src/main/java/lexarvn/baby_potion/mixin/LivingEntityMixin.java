package lexarvn.baby_potion.mixin;

import lexarvn.baby_potion.BabyPotion;
import lexarvn.baby_potion.BabyUtils;
import lexarvn.baby_potion.InfantilismEffect;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.monster.Guardian;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
  @Shadow 
  protected abstract EntityDimensions getDefaultDimensions(final Pose pose);

  @Inject(method = "onEffectsRemoved", at = @At("HEAD"), cancellable = true)
  private void removeBabyEffect(final Collection<MobEffectInstance> effects, CallbackInfo ci) {
    for (MobEffectInstance effect : effects) {
      if (effect.is(BabyPotion.INFANTILISM_EFFECT)) {
        LivingEntity entity = (LivingEntity)(Object)this;
        switch (entity) {
          case Tadpole tadpole -> BabyUtils.convertTadpoleToFrog(tadpole);
          case Guardian guardian -> BabyUtils.convertGuardianToElderGuardian(guardian);
          default -> {
            entity.getAttribute(BabyPotion.MATURITY_SCALE).removeModifier(InfantilismEffect.FORCE_BABY);
          }
        }
      }
    }
  }

  @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
  public void injectCustomHitbox(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
    LivingEntity entity = (LivingEntity)(Object)this;

    if (BabyUtils.maturityScaleIsBaby(entity) && !BabyUtils.isNativeBaby(entity) && !BabyUtils.isTransformable(entity)) {
      var entityType = entity.getType();
      if (BabyUtils.maturityScaleIsChibi(entity) || entityType == EntityType.CAMEL_HUSK) {
        if (entityType == EntityType.PLAYER || entityType == EntityType.MANNEQUIN) {
          cir.setReturnValue(this.getDefaultDimensions(pose));
          return;
        }

        var dimensions = BabyUtils.getBabyDimensions(entity);
        
        if (dimensions != null) {
          cir.setReturnValue(dimensions);
          return;
        }
      }
    }
  }

  @Inject(method = "getScale", at = @At("HEAD"), cancellable = true)
  private void applyInstantInfantileScale(CallbackInfoReturnable<Float> cir) {
    LivingEntity entity = (LivingEntity)(Object)this;

    if (BabyUtils.maturityScaleIsBaby(entity) && !BabyUtils.isNativeBaby(entity) && !BabyUtils.isTransformable(entity)) {
      float customScale = BabyUtils.getBabyScale(entity);
      cir.setReturnValue(customScale);
    }
  }

  @Inject(method = "canBeAffected", at = @At("HEAD"), cancellable = true)
  private void immuneToInfantilism(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
    LivingEntity entity = (LivingEntity)(Object)this;
    if (
      effect.is(BabyPotion.INFANTILISM_EFFECT)
      && !entity.hasEffect(BabyPotion.INFANTILISM_EFFECT)
      && !BabyUtils.maturityScaleIsBaby(entity)
      && BabyUtils.isNotBabyable(entity)
    ) {
      cir.setReturnValue(false); 
    }
  }

  @Inject(method = "createLivingAttributes", at = @At("RETURN"))
  private static void addMaturityScaleAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
    cir.getReturnValue().add(BabyPotion.MATURITY_SCALE);
  }

  @Inject(method = "onAttributeUpdated", at = @At("HEAD"))
  private void debugAttributeSync(final Holder<Attribute> attribute, CallbackInfo ci) {
    LivingEntity entity = (LivingEntity)(Object)this;
    
    if (attribute.is(BabyPotion.MATURITY_SCALE_ID)) {
      entity.refreshDimensions();
    }
  }
}
