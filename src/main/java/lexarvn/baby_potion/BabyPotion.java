package lexarvn.baby_potion;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attribute.Sentiment;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BabyPotion implements ModInitializer {
  public static final String MOD_ID = "baby_potion";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID + "_logger");
  public static final Identifier MATURITY_SCALE_ID = Identifier.fromNamespaceAndPath(MOD_ID, "maturity_scale");

  public static final Holder<@NonNull Attribute> MATURITY_SCALE = Registry.registerForHolder(
    BuiltInRegistries.ATTRIBUTE, 
    MATURITY_SCALE_ID,
    new RangedAttribute("attribute.name.baby_potion.maturity_scale", 0, -2, 1).setSyncable(true).setSentiment(Sentiment.NEUTRAL)
  );

  public static Holder<@NonNull MobEffect> INFANTILISM_EFFECT = Registry.registerForHolder(
    BuiltInRegistries.MOB_EFFECT, 
    Identifier.fromNamespaceAndPath(BabyPotion.MOD_ID, "infantilism_effect"),
    new InfantilismEffect()
  );

  public static Holder<@NonNull Potion> INFANTILISM_POTION = Registry.registerForHolder(
    BuiltInRegistries.POTION, 
    Identifier.fromNamespaceAndPath(BabyPotion.MOD_ID, "infantilism_potion"),
    new Potion("infantilism", new MobEffectInstance(BabyPotion.INFANTILISM_EFFECT, 20 * 60 * 3))
  );

  public static final GameRule<BabyGameRules.SCALE_NON_NATIVE_BABIES> BABY_RULE_SCALE_NON_NATIVE_BABIES = GameRuleBuilder
    .forEnum(BabyGameRules.SCALE_NON_NATIVE_BABIES.OFF)
    .category(GameRuleCategory.MISC)
    .buildAndRegister(Identifier.fromNamespaceAndPath(BabyPotion.MOD_ID, "scale_non_native_babies"));

  @Override
  public void onInitialize() {
    LOGGER.info(MOD_ID + " has been initialized.");
    FabricPotionBrewingBuilder.BUILD.register(builder -> builder.addMix(
      Potions.AWKWARD, 
      Items.GOLDEN_DANDELION,
      INFANTILISM_POTION
    ));
  }
}