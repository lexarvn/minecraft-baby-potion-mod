package lexarvn.baby_potion.mixin.client;

import org.spongepowered.asm.mixin.Mixin;

import lexarvn.baby_potion.IChibiTweaksAccess;
import lexarvn.baby_potion.INativeBabyAccess;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements IChibiTweaksAccess, INativeBabyAccess {
  public boolean chibiTweaks = false;
  public boolean isNativeBaby = false;

  @Override
  public void setUseChibiTweaks(boolean shouldUse) {
    this.chibiTweaks = shouldUse;
  }

  @Override
  public boolean shouldUseChibiTweaks() {
    return this.chibiTweaks;
  }

  @Override
  public void setIsNativeBaby(boolean isNativeBaby) {
    this.isNativeBaby = isNativeBaby;
  }

  @Override
  public boolean getIsNativeBaby() {
    return this.isNativeBaby;
  }
}
