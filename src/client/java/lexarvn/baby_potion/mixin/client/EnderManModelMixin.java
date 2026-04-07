package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lexarvn.baby_potion.IChibiTweaksAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;

@Mixin(EndermanModel.class)
public class EnderManModelMixin extends HumanoidModel<EndermanRenderState> {
  public EnderManModelMixin(final ModelPart root) {
    super(root);
  }

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(final EndermanRenderState state, CallbackInfo ci) {
    if (!((IChibiTweaksAccess) state).shouldUseChibiTweaks()){
      return;
    }

    float headScale = state.isBaby ? 2.0f : 1.0f;

    this.head.xScale = headScale;
    this.head.yScale = headScale;
    this.head.zScale = headScale;
  }
}
