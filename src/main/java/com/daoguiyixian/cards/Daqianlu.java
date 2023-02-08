package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class Daqianlu extends CustomCard {
    public static final String ID = ModHelper.MakePath(Daqianlu.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/Daqianlu.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public Daqianlu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 14;
        this.magicNumber = this.baseMagicNumber = 1;

        this.tags.add(DA_QIAN_LU);
    }

        List<AbstractCard> cards = new ArrayList<>();


        Random random = new Random();


    public void use(AbstractPlayer p, AbstractMonster m) {
//        List<AbstractCard> cards = new ArrayList<>();
        cards.add(new DaqianNail());
        cards.add(new DaqianNail());
        cards.add(new DaqianEye());
        cards.add(new DaqianEye());
        cards.add(new DaqianSkin());
        cards.add(new DaqianSkin());
        cards.add(new DaqianFinger());
        cards.add(new DaqianFinger());
        cards.add(new DaqianSkin());
        cards.add(new DaqianSkin());
        cards.add(new DaqianTooth());
        cards.add(new DaqianTooth());
        cards.add(new DaqianArm());
        cards.add(new DaqianArm());
        cards.add(new DaqianFireSkin());
        cards.add(new DaqianRib());


//
//        Random random = new Random();
        random.nextInt(cards.size());
        //随机放4张卡
        this.addToBot(new MakeTempCardInDrawPileAction(cards.get(random.nextInt(cards.size())), 1, true, true));
        this.addToBot(new MakeTempCardInDrawPileAction(cards.get(random.nextInt(cards.size())), 1, true, true));
        this.addToBot(new MakeTempCardInDrawPileAction(cards.get(random.nextInt(cards.size())), 1, true, true));
        this.addToBot(new MakeTempCardInDrawPileAction(cards.get(random.nextInt(cards.size())), 1, true, true));
//        this.addToBot(new MakeTempCardInHandAction(cards.get(random.nextInt(cards.size())), 1, false));
//        this.addToBot(new MakeTempCardInHandAction(cards.get(random.nextInt(cards.size())), 1, false));
//        this.addToBot(new MakeTempCardInHandAction(cards.get(random.nextInt(cards.size())), 1, false));

//        this.addToBot(new LoseHPAction(p, p, 8));
//        this.addToBot(new GainBlockAction(p, p, this.block));
//        this.addToBot(new GainEnergyAction(this.magicNumber));

//        LihuowangMod.logger.error("===================p.energy.energyMaster===========>"+p.energy.energyMaster);
//        LihuowangMod.logger.error("======================p.energy.energy========>"+p.energy.energy);
//        LihuowangMod.logger.error("===================p.EnergyPanel.totalCount===========>"+ EnergyPanel.totalCount);
//        LihuowangMod.logger.error("==============p.hand.size()================>"+p.hand.size());
//        LihuowangMod.logger.error("=============== p.energy.energy===============>"+ p.energy.energy);
//        LihuowangMod.logger.error("==============================>");
        if(p.currentHealth == 1 && p.hand.size()==1 && (EnergyPanel.totalCount-this.cost) == 0){

           AbstractCard dj =  new DaqianDengJie();
//           dj.updateCost(0);
            dj.setCostForTurn(0);
            this.addToBot(new MakeTempCardInHandAction(dj, 1, false));
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.updateCost(0);
        }
    }


    public AbstractCard makeCopy() {
        return new Daqianlu();
    }


}
