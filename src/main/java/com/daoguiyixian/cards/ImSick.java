package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

//我有病啊！  进入疯癫状态-（Madness-Doubt-Regret-Writhe）洗入卡组
public class ImSick extends CustomCard {
    public static final String ID = ModHelper.MakePath(ImSick.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/ImSick.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public ImSick() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
//        this.baseBlock = 14;
//        this.magicNumber = this.baseMagicNumber = 1;
//        this.exhaust=true;
//
//        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CrazyPower(AbstractDungeon.player, 1), 1, true));
        this.addToBot(new MakeTempCardInDrawPileAction(new Madness(),1,true,true));
        this.addToBot(new MakeTempCardInDrawPileAction(new Doubt(),1,true,true));
        this.addToBot(new MakeTempCardInDrawPileAction(new Regret(),1,true,true));
        this.addToBot(new MakeTempCardInDrawPileAction(new Writhe(),1,true,true));

//        this.addToBot(new LoseHPAction(p, p, 8));
//        this.addToBot(new GainBlockAction(p, p, this.block));
//        this.addToBot(new GainEnergyAction(this.magicNumber));

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new ImSick();
    }

}
