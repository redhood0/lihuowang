package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class WuxingPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModHelper.MakePath("WuxingPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WuxingPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;


        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/wuxing84.png";
        String path48 = "lihuowangResources/img/power/wuxing32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);



        // 首次添加能力更新描述
        this.updateDescription();
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {

//        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));

        return super.atDamageFinalReceive(damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {

        return super.atDamageReceive(damage, damageType);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
//        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
//            this.flash();
//            this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
//        }
        this.addToBot((AbstractGameAction) new HealAction(AbstractDungeon.player, AbstractDungeon.player, damageAmount));

        return damageAmount;
    }

//    @Override
//    public int onLoseHp(int damageAmount) {
//        if (damageAmount > 0) {
//            this.flash();
//            return 0;
//        } else {
//            return damageAmount;
//        }
//    }

    //    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        super.atEndOfTurn(isPlayer);
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "CrazyPower"));
//
//    }
    public void atEndOfRound() {
        super.atEndOfRound();

//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        if (this.justApplied) {
//            this.justApplied = false;
//        } else {

            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "lihuowang:WuxingPower"));

            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "lihuowang:WuxingPower", 1));
            }

            if(this.amount == 1){
                this.addToBot(new LoseHPAction(this.owner,this.owner, 999999));
            }


//        }
    }



    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
