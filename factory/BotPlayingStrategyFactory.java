package tictactoe.factory;

import tictactoe.dto.BotDifficultyLevel;
import tictactoe.strategy.botPlayingStrategy.BotPlayingStrategy;
import tictactoe.strategy.botPlayingStrategy.EasyBotPlayingStartegy;
import tictactoe.strategy.botPlayingStrategy.HardBotPlayingStrategy;
import tictactoe.strategy.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if (botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStartegy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return  new HardBotPlayingStrategy();
        }
        return null;
    }

}
