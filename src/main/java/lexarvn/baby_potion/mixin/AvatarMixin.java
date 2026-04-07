package lexarvn.baby_potion.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;

import lexarvn.baby_potion.BabyUtils;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityAttachment;
import net.minecraft.world.entity.EntityAttachments;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

@Mixin(Avatar.class)
public abstract class AvatarMixin extends LivingEntity {
  protected AvatarMixin(final EntityType<? extends LivingEntity> type, final Level level) {
      super(type, level);
  }
  
  private static final EntityDimensions STANDING_DIMENSIONS = EntityDimensions.scalable(0.49f, 0.84f)
    .withEyeHeight(0.7f)
    .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.2f, 0.0f));
  private static final Map<Pose, EntityDimensions> BABY_POSES = ImmutableMap.<Pose, EntityDimensions>builder()
    .put(Pose.STANDING, STANDING_DIMENSIONS)
    .put(Pose.SLEEPING, SLEEPING_DIMENSIONS)
    .put(Pose.FALL_FLYING, EntityDimensions.scalable(0.49f, 0.49f).withEyeHeight(0.33f))
    .put(Pose.SWIMMING, EntityDimensions.scalable(0.49f, 0.49f).withEyeHeight(0.33f))
    .put(Pose.SPIN_ATTACK, EntityDimensions.scalable(0.49f, 0.49f).withEyeHeight(0.33f))
    .put(Pose.CROUCHING, EntityDimensions.scalable(0.49f, 0.7f).withEyeHeight(0.56f))
    .put(Pose.DYING, EntityDimensions.fixed(0.2f, 0.2f).withEyeHeight(0.7f))
    .build();

  @Inject(method = "getDefaultDimensions", at = @At("RETURN"), cancellable = true)
  public void injectCustomHitbox(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
    Avatar entity = (Avatar)(Object) this;
    if (BabyUtils.maturityScaleIsChibi(entity)) {
      cir.setReturnValue(BABY_POSES.getOrDefault(pose, STANDING_DIMENSIONS));
    }
  }
}
