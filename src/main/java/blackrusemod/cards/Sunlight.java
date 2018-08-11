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

public class Sunlight extends CustomCard {
	public static final String ID = "Sunlight";
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
		if (p.hasPower("KnivesPower")) {
			if (p.getPower("KnivesPower").amount >= 1) {
				AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
				AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY), 0.1F));
				AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
				AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "KnivesPower", 1));
				if (p.hasPower("SurpressingFirePower")) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
					p.addBlock(p.getPower("SurpressingFirePower").amount);
				}
				for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
							new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
					if (AbstractDungeon.player.hasRelic("PaperSwan")) 
						if (AbstractDungeon.cardRandomRng.randomBoolean())
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new AmplifyDamagePower(mo, 1), 1));
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
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) 
			this.baseDamage += AbstractDungeon.player.getPower("SilverBladesPower").amount;
		super.applyPowers();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(1);
		}
	}
}