package lexarvn.baby_potion.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lexarvn.baby_potion.BabyUtils;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.camel.CamelHusk;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.zombie.Zombie;

@Mixin({
  LivingEntity.class, 
  AgeableMob.class, 
  Zombie.class, 
  Piglin.class, 
  Zoglin.class, 
  CamelHusk.class
})
public class BabyStatusMixin {
  @Inject(method = "isBaby", at = @At("HEAD"), cancellable = true)
  public void forceBaby(CallbackInfoReturnable<Boolean> cir) {
    LivingEntity entity = (LivingEntity)(Object) this;
    if (BabyUtils.maturityScaleIsBaby(entity)) {
      cir.setReturnValue(true);
    }
  }
}
