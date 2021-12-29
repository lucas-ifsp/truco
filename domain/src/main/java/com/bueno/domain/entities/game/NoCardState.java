/*
 *  Copyright (C) 2021 Lucas B. R. de Oliveira - IFSP/SCL
 *  Contact: lucas <dot> oliveira <at> ifsp <dot> edu <dot> br
 *
 *  This file is part of CTruco (Truco game for didactic purpose).
 *
 *  CTruco is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CTruco is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CTruco.  If not, see <https://www.gnu.org/licenses/>
 */

package com.bueno.domain.entities.game;

import com.bueno.domain.entities.deck.Card;
import com.bueno.domain.entities.player.util.Player;

import java.util.EnumSet;

class NoCardState implements HandState {

    private final Hand context;

    NoCardState(Hand context) {
        this.context = context;
        setPossibleHandActions();
    }

    private void setPossibleHandActions() {
        final EnumSet<PossibleAction> possibleActions = EnumSet.of(PossibleAction.PLAY);
        if(context.canRaiseBet()) possibleActions.add(PossibleAction.RAISE);
        context.setPossibleActions(possibleActions);
    }

    @Override
    public void playFirstCard(Player player, Card card) {
        context.addOpenCard(card);
        context.setCardToPlayAgainst(card);
        context.setCurrentPlayer(context.getLastToPlay());
        context.setState(new OneCardState(context));
    }

    @Override
    public void playSecondCard(Player player, Card card) {
        throw new IllegalStateException("Can not play a second card before playing a first one.");
    }

    public void accept(Player responder) {
        throw new IllegalStateException("No raising bet request to be accepted.");
    }

    @Override
    public void quit(Player responder) {
        throw new IllegalStateException("No raising bet request to quit.");
    }

    @Override
    public void raiseBet(Player requester) {
        context.addScoreProposal();
        context.setLastBetRaiser(requester);
        context.setCurrentPlayer(context.getLastToPlay());
        context.setState(new WaitingRaiseResponseState(context));
    }
}
