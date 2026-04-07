package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lexarvn.baby_potion.BabyUtils;
import lexarvn.baby_potion.IChibiTweaksAccess;
import lexarvn.baby_potion.INativeBabyAccess;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
  @Inject(method = "extractRenderState", at = @At("TAIL"))
  private void setChibiState(LivingEntity entity, LivingEntityRenderState state, float f, CallbackInfo ci) {
    ((IChibiTweaksAccess) state).setUseChibiTweaks(BabyUtils.maturityScaleIsChibi(entity));
    ((INativeBabyAccess)state).setIsNativeBaby(BabyUtils.isNativeBaby(entity));
  }
}
