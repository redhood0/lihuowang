package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class XinzhuoFangchui extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("XinzhuoFangchui");
    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/xinzhuofangchui.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/xinzhuofangchui.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public XinzhuoFangchui() {

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
        this.flash();
        AbstractPlayer p =AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1),1));
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }

    @Override
    public void atBattleStartPreDraw() {
//        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

    }

    public AbstractRelic makeCopy() {
        return new XinzhuoFangchui();
    }

}
