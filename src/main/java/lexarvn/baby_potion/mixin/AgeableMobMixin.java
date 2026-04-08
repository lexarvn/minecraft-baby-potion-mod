package lexarvn.baby_potion.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import lexarvn.baby_potion.BabyPotion;
import lexarvn.baby_potion.BabyUtils;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;

@Mixin(AgeableMob.class)
public class AgeableMobMixin {
  @Redirect(
    method = "mobInteract",
    at = @At(
      value = "INVOKE", 
      target = "Lnet/minecraft/world/entity/AgeableMob;canUseGoldenDandelion(Lnet/minecraft/world/item/ItemStack;ZILnet/minecraft/world/entity/Mob;)Z"
    )
  )
  private boolean interceptDandelion(ItemStack itemInHand, boolean isBaby, int cooldown, Mob mob) {
    if (BabyUtils.maturityScaleIsBaby(mob)) {
      BabyPotion.LOGGER.info("Mob has infantilism effect, blocking dandelion use.");
      return false;
    }

    return AgeableMob.canUseGoldenDandelion(itemInHand, isBaby, cooldown, mob);
  }
}
