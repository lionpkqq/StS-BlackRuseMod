package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BounceAction;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SilverBladesPower;
import blackrusemod.powers.SurpressingFirePower;
import blackrusemod.vfx.ServantDaggerEffect;

public class Ricochet extends CustomCard {
	public static final String ID = "BlackRuseMod:Ricochet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int BOUNCE = 2;

	public Ricochet() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.RICOCHET), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BOUNCE;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) 
			if (p.getPower(KnivesPower.POWER_ID).amount > 0) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new ServantDaggerEffect(m.hb.cX, m.hb.cY)));
				AbstractDungeon.actionManager.addToBottom(new BounceAction(p, m, this.baseDamage, this.magicNumber+1));
				if (p.hasPower(SurpressingFirePower.POWER_ID)) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
					p.addBlock(p.getPower(SurpressingFirePower.POWER_ID).amount);
				}
				AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, KnivesPower.POWER_ID, 1));
			}
	}

	public AbstractCard makeCopy() {
		return new Ricochet();
	}
	
	public void applyPowers() {
		this.baseDamage = ATTACK_DMG;
		if (AbstractDungeon.player.hasPower(SilverBladesPower.POWER_ID)) 
			this.baseDamage += AbstractDungeon.player.getPower(SilverBladesPower.POWER_ID).amount;
		super.applyPowers();
		if (AbstractDungeon.player.hasPower(SilverBladesPower.POWER_ID))
			this.isDamageModified = true;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}