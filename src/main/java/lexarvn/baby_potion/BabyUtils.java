package lexarvn.baby_potion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.ConversionParams.AfterConversion;
import net.minecraft.world.entity.EntityAttachment;
import net.minecraft.world.entity.EntityAttachments;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;

public class BabyUtils {
  private static final Set<EntityType<?>> NOT_BABYABLE_ENTITY_TYPES = Set.of(
    EntityTypes.TADPOLE,
    EntityTypes.SLIME,
    EntityTypes.MAGMA_CUBE,
    EntityTypes.ALLAY,
    EntityTypes.VEX,
    EntityTypes.SHULKER,
    EntityTypes.SNOW_GOLEM,
    EntityTypes.IRON_GOLEM,
    EntityTypes.COPPER_GOLEM,
    EntityTypes.BLAZE,
    EntityTypes.BREEZE,
    EntityTypes.CREAKING,
    EntityTypes.WITHER,
    EntityTypes.GUARDIAN
  );

  private static final Set<EntityType<?>> NATIVE_BABIES_ENTITY_TYPES = Set.of(
    EntityTypes.ARMADILLO,
    EntityTypes.AXOLOTL,
    EntityTypes.BEE,
    EntityTypes.CAMEL,
    EntityTypes.CAT,
    EntityTypes.CHICKEN,
    EntityTypes.COW,
    EntityTypes.DOLPHIN,
    EntityTypes.DONKEY,
    EntityTypes.DROWNED,
    EntityTypes.FOX,
    EntityTypes.GLOW_SQUID,
    EntityTypes.GOAT,
    EntityTypes.HAPPY_GHAST,
    EntityTypes.HOGLIN,
    EntityTypes.HORSE,
    EntityTypes.HUSK,
    EntityTypes.LLAMA,
    EntityTypes.MOOSHROOM,
    EntityTypes.MULE,
    EntityTypes.NAUTILUS,
    EntityTypes.OCELOT,
    EntityTypes.PANDA,
    EntityTypes.PIG,
    EntityTypes.PIGLIN,
    EntityTypes.POLAR_BEAR,
    EntityTypes.RABBIT,
    EntityTypes.SHEEP,
    EntityTypes.SKELETON_HORSE,
    EntityTypes.SNIFFER,
    EntityTypes.SQUID,
    EntityTypes.STRIDER,
    EntityTypes.TRADER_LLAMA,
    EntityTypes.TURTLE,
    EntityTypes.VILLAGER,
    EntityTypes.WOLF,
    EntityTypes.ZOGLIN,
    EntityTypes.ZOMBIE,
    EntityTypes.ZOMBIE_HORSE,
    EntityTypes.ZOMBIE_VILLAGER,
    EntityTypes.ZOMBIFIED_PIGLIN
  );

  private record BabyScale(float simpleScale, float chibiScale, EntityDimensions chibiDimensions) {}

  private static final Map<EntityType<?>, BabyScale> CUSTOM_ENTITY_BABY_SCALES = new HashMap<>();

  private static void registerBabyScale(BabyScale scales, EntityType<?>... types) {
    for (EntityType<?> type : types) {
        CUSTOM_ENTITY_BABY_SCALES.put(type, scales);
    }
  }

  static {
    registerBabyScale(
      new BabyScale(
        0.49f, 
        0.34f,
        EntityDimensions.scalable(0.49f, 0.99f)
          .withEyeHeight(0.63f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.2f, 0.0f))
      ),
      EntityTypes.VINDICATOR,
      EntityTypes.ILLUSIONER,
      EntityTypes.PILLAGER,
      EntityTypes.EVOKER
    );
    registerBabyScale(
      new BabyScale(
        0.49f, 
        0.34f,
        EntityDimensions.scalable(0.49f, 0.99f).withEyeHeight(0.63f)
      ),
      EntityTypes.WITCH,
      EntityTypes.WANDERING_TRADER
    );
    registerBabyScale(
      new BabyScale(
        0.49f, 
        0.36f,
        EntityDimensions.scalable(0.49f, 0.99f)
          .withEyeHeight(0.78f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.1875f, 0.0f))
      ),
      EntityTypes.PIGLIN_BRUTE
    );
    registerBabyScale(
      new BabyScale(
        0.49f,
        0.38f,
        null
      ),
      EntityTypes.MANNEQUIN,
      EntityTypes.PLAYER
    );
    registerBabyScale(
      new BabyScale(
        0.49f,
        0.38f,
        EntityDimensions.scalable(0.49f, 0.99f)
          .withEyeHeight(0.775f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.25f, 0.0f))
      ),
      EntityTypes.PARCHED,
      EntityTypes.SKELETON,
      EntityTypes.STRAY
    );
    registerBabyScale(
      new BabyScale(
        0.49f,
        0.38f,
        EntityDimensions.scalable(0.49f, 1.2f)
          .withEyeHeight(0.925f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.3f, 0.0f))
      ),
      EntityTypes.WITHER_SKELETON
    );
    registerBabyScale(new BabyScale(
        0.49f, 
        0.41f,
        EntityDimensions.scalable(0.49f, 1.44f)
          .withEyeHeight(1.13f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.1f, 0.0f))
      ), 
      EntityTypes.ENDERMAN
    );
    registerBabyScale(new BabyScale(
        0.49f, 
        0.38f,
        EntityDimensions.scalable(0.75f, 1.44f)
          .withEyeHeight(1.05f)
          .withAttachments(EntityAttachments.builder().attach(EntityAttachment.VEHICLE, 0.0f, 0.1f, 0.0f))
      ), 
      EntityTypes.WARDEN
    );
    registerBabyScale(
      new BabyScale(
        0.49f, 
        0.38f, 
        EntityDimensions.scalable(0.49f, 0.80f).withEyeHeight(0.63f)
      ),
      EntityTypes.CREEPER
    );
    registerBabyScale(
      new BabyScale(0.2f, 0.2f, null),
      EntityTypes.GHAST
    );
    registerBabyScale(
      new BabyScale(
        0.6f, 
        0.6f, 
        EntityDimensions.scalable(1.02f, 1.425f).withEyeHeight(1.365f)
      ),
      EntityTypes.CAMEL_HUSK
    );
  }

  public static EntityDimensions getBabyDimensions(LivingEntity entity) {
    if (maturityScaleIsChibi(entity)) {
      var scales = CUSTOM_ENTITY_BABY_SCALES.get(entity.getType());
      if (scales != null) {
        return scales.chibiDimensions();
      }
    }
    if (entity.getType() == EntityTypes.CAMEL_HUSK) {
      return CUSTOM_ENTITY_BABY_SCALES.get(entity.getType()).chibiDimensions();
    }
    return null;
  }

  public static float getBabyScale(LivingEntity entity) {
    if (CUSTOM_ENTITY_BABY_SCALES.containsKey(entity.getType())) {
      var scales = CUSTOM_ENTITY_BABY_SCALES.get(entity.getType());
      if (maturityScaleIsChibi(entity)) {
        return scales.chibiScale();
      }
      return scales.simpleScale();
    }
    return 0.49f;
  }

  public static boolean isNotBabyable(LivingEntity entity) {
    if(!entity.level().isClientSide()) {
      var ruleValue = entity.level().getServer().getGameRules().get(BabyPotion.BABY_RULE_SCALE_NON_NATIVE_BABIES);
      if (ruleValue == BabyGameRules.SCALE_NON_NATIVE_BABIES.OFF) {
        return entity.isBaby() || !NATIVE_BABIES_ENTITY_TYPES.contains(entity.getType());
      }
    }
    return entity.isBaby() || NOT_BABYABLE_ENTITY_TYPES.contains(entity.getType());
  }

  public static boolean isNativeBaby(LivingEntity entity) {
    return NATIVE_BABIES_ENTITY_TYPES.contains(entity.getType());
  }

  public static boolean isTransformable(LivingEntity entity) {
    var entityType = entity.getType();

    return entityType == EntityTypes.FROG
      || entityType == EntityTypes.TADPOLE
      || entityType == EntityTypes.ELDER_GUARDIAN
      || entityType == EntityTypes.GUARDIAN; 
  }

  public static boolean maturityScaleIsBaby(LivingEntity entity) {
    var maturityScale = entity.getAttribute(BabyPotion.MATURITY_SCALE);
    if (maturityScale == null) {
      return false;
    }
    return maturityScale.getValue() < -0.1f;
  }

  public static boolean maturityScaleIsChibi(LivingEntity entity) {
    return entity.getAttribute(BabyPotion.MATURITY_SCALE).getValue() < -1.1f;
  }

  public static void convertFrogToTadpole(Frog frog, AfterConversion<Tadpole> afterConversion) {
    BabyPotion.LOGGER.info("Transforming " + frog.getName().getString() + " (" + frog.getVariant().getRegisteredName() + ") into a tadpole.");
    frog.convertTo(EntityTypes.TADPOLE, ConversionParams.single(frog, false, false), (tadpole) -> {
      afterConversion.finalizeConversion(tadpole);
      frog.entityTags().forEach(tadpole::addTag);
      tadpole.finalizeSpawn(
        (ServerLevel)frog.level(), 
        ((ServerLevel)frog.level()).getCurrentDifficultyAt(tadpole.blockPosition()),
        EntitySpawnReason.CONVERSION,
        null
      );
      tadpole.setPersistenceRequired();
      tadpole.fudgePositionAfterSizeChange(frog.getDimensions(frog.getPose()));
      frog.playSound(SoundEvents.TADPOLE_FLOP, 0.15F, 1.0F);
    });
  }

  public static void convertTadpoleToFrog(Tadpole tadpole) {
    BabyPotion.LOGGER.info("Transforming " + tadpole.getName().getString() + " into a frog.");
    tadpole.convertTo(EntityTypes.FROG, ConversionParams.single(tadpole, false, false), (frog) -> {
      tadpole.entityTags().forEach(frog::addTag);
      frog.finalizeSpawn(
        (ServerLevel)tadpole.level(),
        ((ServerLevel)tadpole.level()).getCurrentDifficultyAt(frog.blockPosition()),
        EntitySpawnReason.CONVERSION,
        null
      );
      frog.setPersistenceRequired();
      frog.fudgePositionAfterSizeChange(tadpole.getDimensions(tadpole.getPose()));
      tadpole.playSound(SoundEvents.TADPOLE_GROW_UP, 0.15F, 1.0F);
    });
  }

  public static void convertElderGuardianToGuardian(ElderGuardian elderGuardian, AfterConversion<Guardian> afterConversion) {
    BabyPotion.LOGGER.info("Transforming " + elderGuardian.getName().getString() + " into a guardian.");
    elderGuardian.convertTo(EntityTypes.GUARDIAN, ConversionParams.single(elderGuardian, false, false), (guardian) -> {
      afterConversion.finalizeConversion(guardian);
      elderGuardian.entityTags().forEach(guardian::addTag);
      guardian.finalizeSpawn(
        (ServerLevel)elderGuardian.level(),
        ((ServerLevel)elderGuardian.level()).getCurrentDifficultyAt(guardian.blockPosition()),
        EntitySpawnReason.CONVERSION,
        null
      );
      guardian.setPersistenceRequired();
      guardian.fudgePositionAfterSizeChange(elderGuardian.getDimensions(elderGuardian.getPose()));
      elderGuardian.playSound(SoundEvents.GUARDIAN_FLOP, 0.15F, 1.0F);
    });
  }

  public static void convertGuardianToElderGuardian(Guardian guardian) {
    BabyPotion.LOGGER.info("Transforming " + guardian.getName().getString() + " into an elder guardian.");
    guardian.convertTo(EntityTypes.ELDER_GUARDIAN, ConversionParams.single(guardian, false, false), (elderGuardian) -> {
      guardian.entityTags().forEach(elderGuardian::addTag);
      elderGuardian.finalizeSpawn(
        (ServerLevel)guardian.level(),
        ((ServerLevel)guardian.level()).getCurrentDifficultyAt(elderGuardian.blockPosition()),
        EntitySpawnReason.CONVERSION,
        null
      );
      elderGuardian.setPersistenceRequired();
      elderGuardian.fudgePositionAfterSizeChange(guardian.getDimensions(guardian.getPose()));
      guardian.playSound(SoundEvents.ELDER_GUARDIAN_FLOP, 0.15F, 1.0F);
    });
  }
}
