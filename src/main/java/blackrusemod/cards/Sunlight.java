package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SilverBladesPower;
import blackrusemod.powers.SurpressingFirePower;

public class Sunlight extends CustomCard {
	public static final String ID = "BlackRuseMod:Sunlight";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 16;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int BLIGHT = 1;
	
	public Sunlight() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SUNLIGHT), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.isMultiDamage = true;
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BLIGHT;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) {
			if (p.getPower(KnivesPower.POWER_ID).amount >= 1) {
				AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
				AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1f));
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
				AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, KnivesPower.POWER_ID, 1));
				if (p.hasPower(SurpressingFirePower.POWER_ID)) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
					p.addBlock(p.getPower(SurpressingFirePower.POWER_ID).amount);
				}
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
							new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
				}
			}
		}
	}

	public AbstractCard makeCopy() {
		return new Sunlight();
	}
	
	public void applyPowers() {
		if (canUpgrade()) this.baseDamage = ATTACK_DMG;
		else this.baseDamage = ATTACK_DMG + UPGRADE_PLUS_DMG;
		if (AbstractDungeon.player.hasPower(SilverBladesPower.POWER_ID)) 
			this.baseDamage += AbstractDungeon.player.getPower(SilverBladesPower.POWER_ID).amount;
		super.applyPowers();
		if (AbstractDungeon.player.hasPower(SilverBladesPower.POWER_ID))
			this.isDamageModified = true;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(1);
		}
	}
}