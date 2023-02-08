package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.CrazyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.curses.Writhe;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.CorpseExplosionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//消耗生命上限-控制-伤害
public class GhostRing extends CustomCard {
    public static final String ID = ModHelper.MakePath(GhostRing.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/GhostRing.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;

    public GhostRing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
//        this.baseDamage=this.damage = 10;
        this.magicNumber = this.baseMagicNumber = 0;
//        this.exhaust=true;
//
//        this.tags.add(DA_QIAN_LU);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.decreaseMaxHealth(5);//(this.increaseHpAmount, false);
        this.addToBot(new ApplyPowerAction(m, p, new CorpseExplosionPower(m), 1, AbstractGameAction.AttackEffect.POISON));
        this.addToBot(new ApplyPowerAction(m, p, new SlowPower(m, this.magicNumber), this.magicNumber));
        if(this.upgraded){
            this.addToBot(new DamageAction(m,new DamageInfo(p,40)));
        }else {
            this.addToBot(new DamageAction(m,new DamageInfo(p,30)));
        }



//        this.addToBot(new LoseHPAction(p, p, 8));
//        this.addToBot(new GainBlockAction(p, p, this.block));
//        this.addToBot(new GainEnergyAction(this.magicNumber));

    }


    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.maxHealth > 5){
            return true;
        }
        return false;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
//            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new GhostRing();
    }
}
