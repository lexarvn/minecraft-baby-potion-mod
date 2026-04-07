package lexarvn.baby_potion;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.ElderGuardian;

public class InfantilismEffect extends MobEffect {
  public static final String ORIGINAL_FROG_VARIANT_TAG_PREFIX = "original_frog_variant:";
  public static final Identifier FORCE_BABY = Identifier.fromNamespaceAndPath(BabyPotion.MOD_ID, "force_baby");
  private static final AttributeModifier forceBabyModifier = new AttributeModifier(
    FORCE_BABY,
    -1.0f, 
    AttributeModifier.Operation.ADD_VALUE
  );
  private static final AttributeModifier forceChibiModifier = new AttributeModifier(
    FORCE_BABY,
    -2.0f, 
    AttributeModifier.Operation.ADD_VALUE
  );

  public InfantilismEffect() {
    super(MobEffectCategory.NEUTRAL, 0xe1ac12);
  }

  @Override
  public void onEffectStarted(final LivingEntity mob, final int amplifier) {
    var ruleValue = mob.level().getServer().getGameRules().get(BabyPotion.BABY_RULE_SCALE_NON_NATIVE_BABIES);
    var modifierToApply = ruleValue == BabyGameRules.SCALE_NON_NATIVE_BABIES.CHIBI ? forceChibiModifier : forceBabyModifier;

    switch (mob) {
      case Frog frog -> BabyUtils.convertFrogToTadpole(frog, (tadpole) -> {
        tadpole.getAttribute(BabyPotion.MATURITY_SCALE)
          .addOrReplacePermanentModifier(modifierToApply);
        tadpole.addEffect(frog.getEffect(BabyPotion.INFANTILISM_EFFECT));
        tadpole.addTag(InfantilismEffect.ORIGINAL_FROG_VARIANT_TAG_PREFIX + frog.getVariant().getRegisteredName());
      });

      case ElderGuardian elderGuardian -> BabyUtils.convertElderGuardianToGuardian(elderGuardian, (guardian) -> {
        guardian.getAttribute(BabyPotion.MATURITY_SCALE)
          .addOrReplacePermanentModifier(modifierToApply);
        guardian.addEffect(elderGuardian.getEffect(BabyPotion.INFANTILISM_EFFECT));
      });

      default -> {
        mob.getAttribute(BabyPotion.MATURITY_SCALE)
          .addOrReplacePermanentModifier(modifierToApply);
      }
    }
  }
}
