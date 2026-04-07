package lexarvn.baby_potion.mixin.client;

import lexarvn.baby_potion.IChibiTweaksAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;

@Mixin(PiglinModel.class)
public class PiglinModelMixin extends HumanoidModel<PiglinRenderState> {
  public PiglinModelMixin(ModelPart root) {
    super(root);
  }

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(final PiglinRenderState state, CallbackInfo ci) {
    if (!((IChibiTweaksAccess) state).shouldUseChibiTweaks()){
      return;
    }

    float headScale = state.isBrute && state.isBaby ? 2.5f : 1.0f;

    this.head.xScale = headScale;
    this.head.yScale = headScale;
    this.head.zScale = headScale;
  }
}
