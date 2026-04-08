package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;

import lexarvn.baby_potion.IChibiTweaksAccess;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements IChibiTweaksAccess {
  public boolean chibiTweaks = false;

  @Override
  public void setUseChibiTweaks(boolean shouldUse) {
    this.chibiTweaks = shouldUse;
  }

  @Override
  public boolean shouldUseChibiTweaks() {
    return this.chibiTweaks;
  }
}
