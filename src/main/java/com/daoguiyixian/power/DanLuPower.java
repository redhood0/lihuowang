package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.action.BlackTaiSuiAction;
import com.daoguiyixian.cards.*;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DanLuPower extends AbstractPower {

    public static final String POWER_ID = ModHelper.MakePath("DanLuPower");


    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public DanLuPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;

//        this.loadRegion("tools");
        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/danlu84.png";
        String path48 = "lihuowangResources/img/power/danlu32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
        this.priority = 25;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
//        }


    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        //随机加入一张丹药进手牌
        List<AbstractCard> cards = new ArrayList<>();


        Random random = new Random();
        cards.add(new DaLiDan());
        cards.add(new RunXueDan());
        cards.add(new BiGuDan());
        cards.add(new YangShouDan());



        int n = random.nextInt(1000);

        if(0 <= n && n <= 250){
            this.addToBot(new MakeTempCardInHandAction(cards.get(0), 1, false));
        }else if(250 < n && n <= 550){
            this.addToBot(new MakeTempCardInHandAction(cards.get(1), 1, false));
        }else if(550 < n && n <= 997){
            this.addToBot(new MakeTempCardInHandAction(cards.get(2), 1, false));
        }else {
            this.addToBot(new MakeTempCardInHandAction(cards.get(3), 1, false));
        }


    }

}
