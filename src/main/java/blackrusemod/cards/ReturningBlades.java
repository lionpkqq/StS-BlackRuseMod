package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class ReturningBlades extends CustomCard {
	public static final String ID = "ReturningBlades";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 2;

	public ReturningBlades() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.RETURNING_BLADES), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower("ConsumedKnivesPower")) {
			for (int i = 0; i < -p.getPower("ConsumedKnivesPower").amount; i++)
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
	}

	public AbstractCard makeCopy() {
		return new ReturningBlades();
	}
	
	public void applyPowers() {
		this.baseDamage = ATTACK_DMG;
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) 
			this.baseDamage += AbstractDungeon.player.getPower("SilverBladesPower").amount;
		super.applyPowers();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}