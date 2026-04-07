package lexarvn.baby_potion.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lexarvn.baby_potion.BabyPotion;
import lexarvn.baby_potion.InfantilismEffect;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.level.ServerLevelAccessor;


@Mixin(Frog.class)
public class FrogMixin {
  @Shadow 
  private void setVariant(final Holder<FrogVariant> variant) {}

  @Inject(method = "finalizeSpawn", at = @At("TAIL"))
  private void overrideVarient(final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, final @Nullable SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> ci) {
    Frog frog = (Frog)(Object)this;
    String variantTag = frog.entityTags().stream().filter(tag -> tag.startsWith(InfantilismEffect.ORIGINAL_FROG_VARIANT_TAG_PREFIX)).findFirst().orElse(null);
    if (variantTag != null) {
      String variantName = variantTag.substring(InfantilismEffect.ORIGINAL_FROG_VARIANT_TAG_PREFIX.length());
      BabyPotion.LOGGER.info("Setting frog variant to " + variantName);
      Registry<FrogVariant> registry = frog.level().registryAccess().lookupOrThrow(Registries.FROG_VARIANT);
      var variantHolder = registry.get(Identifier.parse(variantName));

      variantHolder.ifPresent(this::setVariant);
    }
  }
}