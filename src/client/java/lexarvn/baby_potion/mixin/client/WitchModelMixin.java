package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lexarvn.baby_potion.IChibiTweaksAccess;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.renderer.entity.state.WitchRenderState;

@Mixin(WitchModel.class)
public class WitchModelMixin {
  @Shadow @Final private ModelPart head;

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(final WitchRenderState state, CallbackInfo ci) {
    if (!((IChibiTweaksAccess) state).shouldUseChibiTweaks()){
      return;
    }

    float headScale = state.isBaby ? 3.0f : 1.0f;

    this.head.xScale = headScale;
    this.head.yScale = headScale;
    this.head.zScale = headScale;
  }
}
