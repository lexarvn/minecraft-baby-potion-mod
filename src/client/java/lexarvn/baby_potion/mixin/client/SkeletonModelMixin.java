package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lexarvn.baby_potion.IChibiTweaksAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

@Mixin(SkeletonModel.class)
public class SkeletonModelMixin<S extends SkeletonRenderState> extends HumanoidModel<S> {
  public SkeletonModelMixin(ModelPart root) {
    super(root);
  }

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(final S state, CallbackInfo ci) {
    if (((IChibiTweaksAccess)state).shouldUseChibiTweaks()){
      float headScale = 2.0f;

      this.head.xScale = headScale;
      this.head.yScale = headScale;
      this.head.zScale = headScale;
    }
  }
}
