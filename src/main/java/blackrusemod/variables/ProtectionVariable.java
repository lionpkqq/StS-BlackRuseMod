package blackrusemod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;
import blackrusemod.cards.AbstractServantCard;

public class ProtectionVariable extends DynamicVariable {
	@Override
	public String key() { return "BRM_P"; }

	@Override
	public boolean isModified(AbstractCard c) {
		if(c instanceof AbstractServantCard) {
			AbstractServantCard servantCard = (AbstractServantCard)c;
			return servantCard.protection != servantCard.baseProtection;
		}
		return false;
	}

	@Override
	public int baseValue(AbstractCard c) { 
		if(c instanceof AbstractServantCard) {
			AbstractServantCard servantCard = (AbstractServantCard)c;
			return servantCard.baseProtection;
		}
		return 0; 
	}

	@Override
	public int value(AbstractCard c) { 
		if(c instanceof AbstractServantCard) {
			AbstractServantCard servantCard = (AbstractServantCard)c;
			return servantCard.protection;
		}
		return 0;
	}

	@Override
	public boolean upgraded(AbstractCard c) { 
		if(c instanceof AbstractServantCard) {
			AbstractServantCard servantCard = (AbstractServantCard)c;
			return servantCard.protectionUpgraded;
		}
		return false;
	}
}