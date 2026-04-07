package lexarvn.baby_potion.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lexarvn.baby_potion.BabyPotion;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.AbstractHorse;


@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin {
  @Inject(method = "canUseSlot", at = @At("HEAD"), cancellable = true)
  private void allowBabySaddles(EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
    AbstractHorse horse = (AbstractHorse) (Object) this;
    
    if (slot == EquipmentSlot.SADDLE) {
      if (horse.hasEffect(BabyPotion.INFANTILISM_EFFECT)) {
        cir.setReturnValue(horse.isAlive() && horse.isTamed());
      }
    }
  }
}