package lexarvn.baby_potion.mixin.client;

import lexarvn.baby_potion.IChibiTweaksAccess;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;

@Mixin(VillagerModel.class)
public class VillagerModelMixin {
  @Shadow @Final private ModelPart head;

  @Inject(method = "setupAnim", at = @At("TAIL"))
  public void applyHeadScale(VillagerRenderState state, CallbackInfo ci) {
    if (((IChibiTweaksAccess)state).shouldUseChibiTweaks()){
      float headScale = 3.0f;

      this.head.xScale = headScale;
      this.head.yScale = headScale;
      this.head.zScale = headScale;
    }
  }
}
