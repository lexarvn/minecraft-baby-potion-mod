package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lexarvn.baby_potion.INativeBabyAccess;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;

@Mixin(ItemInHandLayer.class)
public class ItemInHandLayerMixin<S extends ArmedEntityRenderState> {
  @Inject(method = "useBabyOffset", at = @At("TAIL"), cancellable = true)
  public void shouldApplyBabyOffset(final S state, CallbackInfoReturnable<Boolean> ci) {
    boolean isNativeBaby = ((INativeBabyAccess)state).getIsNativeBaby();
    if (!isNativeBaby && state.isBaby) {
      ci.setReturnValue(false);
    }
  }
}
