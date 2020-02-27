package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BounceAction;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SuppressingFirePower;
import blackrusemod.vfx.ServantDaggerEffect;

public class Ricochet extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Ricochet.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("ricochet.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int BOUNCE = 2;

	public Ricochet() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BOUNCE;
		this.tags.add(Enums.SILVER_BLADES);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) {
			if (p.getPower(KnivesPower.POWER_ID).amount > 0) {
				addToBot(new VFXAction(new ServantDaggerEffect(m.hb.cX, m.hb.cY)));
				addToBot(new BounceAction(p, m, this.damage, this.magicNumber+1));
				if (p.hasPower(SuppressingFirePower.POWER_ID)) {
					AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AttackEffect.SHIELD));
					p.addBlock(p.getPower(SuppressingFirePower.POWER_ID).amount);
				}
				addToBot(new ReducePowerAction(p, p, KnivesPower.POWER_ID, 1));
			}
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Ricochet();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}