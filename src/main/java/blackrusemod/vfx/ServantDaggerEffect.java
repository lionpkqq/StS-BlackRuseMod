package blackrusemod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ServantDaggerEffect extends AbstractGameEffect {
	private float x;
	private float y;
	private float destY;
	private static final float DUR = 0.4F;
	private TextureAtlas.AtlasRegion img;
	private boolean playedSound = false;
	private boolean forcedAngle = false;
	
	public ServantDaggerEffect(float x, float y) {
		this.img = ImageMaster.DAGGER_STREAK;
		this.x = x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F;
		this.destY = y;
		this.y = this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F;
		this.startingDuration = 0.4F;
		this.duration = 0.4F;
		this.scale = Settings.scale;
		this.rotation = MathUtils.random(-3.0F, 3.0F);
		this.color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public ServantDaggerEffect(float x, float y, float fAngle) {
		this.img = ImageMaster.DAGGER_STREAK;
		this.x = x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F;
		this.destY = y;
		this.y = this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F;
		this.startingDuration = 0.4F;
		this.duration = DUR;
		this.scale = Settings.scale;
		this.rotation = fAngle;
		this.color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
		this.forcedAngle = true;
	}
	
	private void playRandomSfX() {
		int roll = MathUtils.random(5) + 1;
		CardCrawlGame.sound.play("ATTACK_DAGGER_" + roll);
	}
	
	@Override
	public void update() {
		if (!this.playedSound) {
			playRandomSfX();
			this.playedSound = true;
		}

		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
		}
		if (this.duration > 0.2F) {
			this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.2F) * 5.0F);
		} else {
			this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
		}
		this.scale = Interpolation.bounceIn.apply(Settings.scale * 0.5F, Settings.scale * 1.5F, this.duration / 0.4F);
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		if (!this.forcedAngle) {
			sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
			sb.setBlendFunction(770, 1);
			sb.draw(this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
			sb.setBlendFunction(770, 771);
		}
		else {
			sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
			sb.setBlendFunction(770, 1);
			sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
			sb.setBlendFunction(770, 771);
		}
	}

	@Override
	public void dispose() {}
}
