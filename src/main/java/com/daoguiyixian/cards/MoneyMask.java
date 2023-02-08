package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//铜钱面具-使用获得多重护甲2点 和钱-2费 -20金币

public class MoneyMask extends CustomCard {
    public static final String ID = ModHelper.MakePath(MoneyMask.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MoneyMask.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.POWER;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private int increaseGold = 20;
    public MoneyMask() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new PlatedArmorPower(p, this.baseMagicNumber), this.baseMagicNumber));
        if(this.upgraded){
            AbstractDungeon.player.gainGold(20);
        }else {
            AbstractDungeon.player.gainGold(15);
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new MoneyMask();
    }

}
