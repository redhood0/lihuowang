package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.MoonWindAction;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.daoguiyixian.power.CrazyPower;
import com.daoguiyixian.power.MoneyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//消耗20打15
public class MoneySword extends CustomCard {
    public static final String ID = ModHelper.MakePath(MoneySword.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/MoneySword.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static int usetime = 0;

    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    public MoneySword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 12;
        this.baseMagicNumber = 20;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(2); // 将该卡牌的伤害提高3点。
            this.upgradeMagicNumber(-5);
            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
//            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
        }


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

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.loseGold(this.magicNumber);



        if(p.hasPower(ModHelper.MakePath("MoneyPower"))){
            AbstractPower moneyPower =  p.getPower(ModHelper.MakePath("MoneyPower"));

            int amount = moneyPower.amount;
            for(int i = 0 ; i <= amount; i++){
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            if(amount<5){
                this.addToTop(new ApplyPowerAction(p, p, new MoneyPower(p)));
            }
        }else {
            this.addToTop(new ApplyPowerAction(p, p, new MoneyPower(p)));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }


        usetime++;
        LihuowangMod.logger.error("+++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>");
        LihuowangMod.logger.error("++++++++++++++++++usetime+++++>>>>>>>>>>>>>>>>>>>"+usetime);
        LihuowangMod.logger.error("+++++++++++++++++++++++>>>>>>>>>>>>>>>>>>>");
//        this.addToBot(new MoonWindAction(p,m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));

    }



    public AbstractCard makeCopy() {
        return new MoneySword();
    }
}
