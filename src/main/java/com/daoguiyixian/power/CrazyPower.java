package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CrazyPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModHelper.MakePath("CrazyPower");
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrazyPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = -1;

        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/fengdian84.png";
        String path48 = "lihuowangResources/img/power/fengdian32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);



        // 首次添加能力更新描述
        this.updateDescription();
    }

//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        super.atEndOfTurn(isPlayer);
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "CrazyPower"));
//
//    }
    public void atEndOfRound() {
        super.atEndOfRound();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        if (this.justApplied) {
//            this.justApplied = false;
//        } else {
//            if (this.amount == 0) {
//                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vulnerable"));
//            } else {
//                this.addToBot(new ReducePowerAction(this.owner, this.owner, "Vulnerable", 1));
//            }
//
//        }
    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
//        if (type == DamageInfo.DamageType.NORMAL) {
//            if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
//                return damage * 1.25F;
//            } else {
//                return this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog") ? damage * 1.75F : damage * 1.5F;
//            }
//        } else {
//            return damage;
//        }
        return damage * 0.75F;
    }



    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
//        DamageType.SB ==DamageInfo.DamageType.NORMAL;
        if(DamageInfo.DamageType.NORMAL == type || DamageInfo.DamageType.HP_LOSS == type){
            return damage * 1.5f;
        }else {
            return damage;
        }
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }



}
