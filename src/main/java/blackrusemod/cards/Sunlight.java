package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SuppressingFirePower;

public class Sunlight extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Sunlight.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("sunlight.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 16;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int BLIGHT = 1;
	
	public Sunlight() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isMultiDamage = true;
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BLIGHT;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) {
			if (p.getPower(KnivesPower.POWER_ID).amount >= 1) {
				addToBot(new SFXAction("ATTACK_HEAVY"));
				addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1f));
				addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AttackEffect.FIRE));
				addToBot(new ReducePowerAction(p, p, KnivesPower.POWER_ID, 1));
				if (p.hasPower(SuppressingFirePower.POWER_ID)) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AttackEffect.SHIELD));
					p.addBlock(p.getPower(SuppressingFirePower.POWER_ID).amount);
				}
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
					addToBot(new ApplyPowerAction(mo, p, new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
				}
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Sunlight();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(1);
		}
	}
}