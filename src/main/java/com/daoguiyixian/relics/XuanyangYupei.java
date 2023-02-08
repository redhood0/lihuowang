package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.cards.BreakSpace;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class XuanyangYupei extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("XuanyangYupei");
    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/yupei.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/yupei.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public XuanyangYupei() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);

//        tips.clear();
//        tips.add(new PowerTip(name, description));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    //效果不好就换三回合



    @Override
    public void atBattleStart() {
        super.atBattleStart();
//        this.counter=0;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }

    @Override
    public void atBattleStartPreDraw() {
//        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new MakeTempCardInHandAction(new BreakSpace(), 1, false));
    }

    public AbstractRelic makeCopy() {
        return new XuanyangYupei();
    }


}
