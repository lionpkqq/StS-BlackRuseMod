package blackrusemod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class BounceEffect extends AbstractGameEffect {
	private static TextureAtlas.AtlasRegion img;
	private float sX;
	private float sY;
	private float cX;
	private float cY;
	private float dX;
	private float dY;
	private float yOffset;
	private float bounceHeight;
	private static final float DUR = 0.3F;
	private boolean playedSfx = false;
	private ArrayList<Vector2> previousPos = new ArrayList<Vector2>();
	
	public BounceEffect(float srcX, float srcY, float destX, float destY) {
		if (img == null) {
			img = ImageMaster.DAGGER_STREAK;
		}
		
		this.sX = srcX;
		this.sY = srcY;
		this.cX = this.sX;
		this.cY = this.sY;
		this.dX = destX;
		this.dY = destY;
		this.rotation = 0.0F;
		this.duration = DUR;
		
		this.color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
		
		if (this.sY > this.dY) {
			this.bounceHeight = 0;
		} else {
			this.bounceHeight = (this.dY - this.sY + 0);
		}
	}
	
	@Override
	public void update() {
		if (!this.playedSfx) {
			this.playedSfx = true;
			if (MathUtils.randomBoolean()) {
				CardCrawlGame.sound.playA("ATTACK_DAGGER_1", MathUtils.random(-0.3F, -0.2F));
			} else {
				CardCrawlGame.sound.playA("ATTACK_DAGGER_2", MathUtils.random(-0.3F, -0.2F));
			}
		}
		
		this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.6F);
		this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.6F);
		
		this.previousPos.add(new Vector2(this.cX + 
			MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.cY + this.yOffset + 
			MathUtils.random(-30.0F, 30.0F) * Settings.scale));
		if (this.previousPos.size() > 20) {
			this.previousPos.remove(this.previousPos.get(0));
		}
		
		if (this.dX > this.sX) {
			this.rotation -= Gdx.graphics.getDeltaTime() * 1000.0F;
		} else {
			this.rotation += Gdx.graphics.getDeltaTime() * 1000.0F;
		}
		
		if (this.duration > 0.3F) {
			this.color.a = (Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 0.3F) / 0.3F) * Settings.scale);
			this.yOffset = (Interpolation.circleIn.apply(this.bounceHeight, 0.0F, (this.duration - 0.3F) / 0.3F) * Settings.scale);
		} else {
			this.yOffset = (Interpolation.circleOut.apply(0.0F, this.bounceHeight, this.duration / 0.3F) * Settings.scale);
		}
		
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setBlendFunction(770, 1);
		sb.setColor(new Color(0.4F, 1.0F, 1.0F, this.color.a / 3.0F));
		
		for (int i = 5; i < this.previousPos.size(); i++) {
			sb.draw(ImageMaster.POWER_UP_2, 
				(this.previousPos.get(i)).x - img.packedWidth / 2, 
				(this.previousPos.get(i)).y - img.packedHeight / 2, 
					img.packedWidth / 2.0F, 
					img.packedHeight / 2.0F, 
					img.packedWidth, 
					img.packedHeight, 
					this.scale / (80.0F / i), 
					this.scale / (80.0F / i), 
					this.rotation);
		}

		sb.setColor(this.color);
		sb.draw(img, 
				this.cX - img.packedWidth / 2, 
				this.cY - img.packedHeight / 2 + this.yOffset, 
				img.packedWidth / 2.0F, 
				img.packedHeight / 2.0F, 
				img.packedWidth, 
				img.packedHeight, 
				this.scale / 4.0F, 
				this.scale / 4.0F, 
				this.rotation);

		sb.draw(img, 
				this.cX - img.packedWidth / 2, 
				this.cY - img.packedHeight / 2 + this.yOffset, 
				img.packedWidth / 2.0F, 
				img.packedHeight / 2.0F, 
				img.packedWidth, 
				img.packedHeight, 
				this.scale / 4.0F, 
				this.scale / 4.0F, 
				this.rotation);

		sb.setBlendFunction(770, 771);
	}

	@Override
	public void dispose() {}
}