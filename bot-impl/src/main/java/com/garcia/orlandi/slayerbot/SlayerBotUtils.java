package com.garcia.orlandi.slayerbot;

import com.bueno.spi.model.CardRank;
import com.bueno.spi.model.CardSuit;
import com.bueno.spi.model.TrucoCard;

import java.util.Comparator;
import java.util.List;

public class SlayerBotUtils {

    public List<TrucoCard> getManilhas(List<TrucoCard> cards, TrucoCard vira){
        return cards.stream().filter(card -> card.isManilha(vira)).toList();
    }

    public TrucoCard playWeakestManilha(List<TrucoCard> manilhas) {
        return manilhas.stream()
                .min(Comparator.comparingInt(card -> card.relativeValue(null)))
                .orElse(null);
    }
    public TrucoCard getStrongestCard( List<TrucoCard> cards, TrucoCard vira){
        TrucoCard strongerCard = cards.get(0);

        for (TrucoCard card : cards) {
            int comparison = card.compareValueTo(strongerCard, vira);
            if (comparison > 0) {
                strongerCard = card;
            }
        }

        return strongerCard;
    }

    public TrucoCard getWeakestCard( List<TrucoCard> cards, TrucoCard vira){
        TrucoCard weakerCard = cards.get(0);

        for (TrucoCard card : cards) {
            int comparison = card.compareValueTo(weakerCard, vira);
            if (comparison < 0) {
                weakerCard = card;
            }
        }

        return weakerCard;

    }
}