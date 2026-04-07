package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lexarvn.baby_potion.IChibiTweaksAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;

@Mixin(PlayerModel.class)
public class PlayerModelMixin extends HumanoidModel<AvatarRenderState> {
  public PlayerModelMixin(ModelPart root) {
    super(root);
  }

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(final AvatarRenderState state, CallbackInfo ci) {
    if (!((IChibiTweaksAccess) state).shouldUseChibiTweaks()){
      return;
    }

    float headScale = state.isBaby ? 2.0f : 1.0f;

    this.head.xScale = headScale;
    this.head.yScale = headScale;
    this.head.zScale = headScale;
  }
}
