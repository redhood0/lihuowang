package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.MoneyAgaisntGhostAction;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.CrazyPower;
import com.daoguiyixian.power.MoneyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

//铜钱剑-避邪 1费花钱-加计数-护甲-消耗诅咒翻倍     （1费7甲消耗-1费9甲）
public class MoneyAgainstGhost extends CustomCard {

    public static final String ID = ModHelper.MakePath(MoneyAgainstGhost.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MoneyAgainstGhost.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public MoneyAgainstGhost() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 10;

        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        p.loseGold(this.magicNumber);
//        this.addToTop(new ApplyPowerAction(p, p, new MoneyPower(p)));
        this.addToBot(new MoneyAgaisntGhostAction(p,this.block));
        this.addToBot(new GainBlockAction(p, p, this.block));

        if(p.hasPower(ModHelper.MakePath("MoneyPower"))){
            AbstractPower moneyPower =  p.getPower(ModHelper.MakePath("MoneyPower"));
            int amount = moneyPower.amount;
            if(amount<5){
                this.addToTop(new ApplyPowerAction(p, p, new MoneyPower(p)));
            }
        }else {
            this.addToTop(new ApplyPowerAction(p, p, new MoneyPower(p)));
        }


    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new MoneyAgainstGhost();
    }


    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);

        if (!canUse) {
            return false;
        } else {
            if(AbstractDungeon.player.gold < this.baseMagicNumber){
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            }
            return canUse;
        }
    }
}
