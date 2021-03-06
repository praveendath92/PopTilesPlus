package in.co.praveenkumar.poptilesplus;

import in.co.praveenkumar.poptilesplus.MainActivity.GamePlayService;
import in.co.praveenkumar.poptilesplus.helper.Database;
import in.co.praveenkumar.poptilesplus.helper.Session;

import java.util.Calendar;

import android.util.Log;

public class AcheivementUnlocker {
	private final String DEBUG_TAG = "AcheivementUnlocker";
	// Achievement unlocking score marks;

	// Hexadecimal mode marks
	private final int HEX_COPYPASTER = 20;
	private final int HEX_GET_IT_DONE = 50;
	private final int HEX_CODER = 100;
	private final int HEX_PRO_CODER = 200;
	private final int HEX_NINJA_PROG = 500;
	private final int HEX_LINUS = 5000;

	// Decimal mode marks
	private final int DEC_NOOB = 20;
	private final int DEC_BEGINNER = 50;
	private final int DEC_QUICKY = 100;
	private final int DEC_PRO = 200;
	private final int DEC_NINJA = 500;

	// Binary mode marks
	private final int BIN_COLLEGE = 20;
	private final int BIN_STUDIOUS = 50;
	private final int BIN_GEEK = 100;
	private final int BIN_NERD = 200;
	private final int BIN_NERDY_GEEK = 500;

	// Fibonnaci mode marks
	private final int FIB_OH_FIBONNACI = 20;
	private final int FIB_FIBONNACI_50 = 50;
	private final int FIB_FIBONNACI_75 = 75;
	private final int FIB_FIBONNACI_CENTURY = 100;
	private final int FIB_FIBONNACI_150 = 200;
	private final int FIB_LENARDO_FIBONNACI = 500;

	GamePlayService playService;
	Database db;

	public AcheivementUnlocker(GamePlayService playService, Database db) {
		this.playService = playService;
		this.db = db;
	}

	/**
	 * Checks and unlocks any score based achievements
	 */
	public void checkForScoreUnlocks() {
		int highscore = db.getHighscore(Session.gameMode);

		// Possible achievements unlocked already
		if (Session.score() < highscore)
			return;

		switch (Session.gameMode) {
		case Session.GAME_MODE_BIN:
			checkBinModeUnlocks();
			break;
		case Session.GAME_MODE_DEC:
			checkDecModeUnlocks();
			break;
		case Session.GAME_MODE_HEX:
			checkHexModeUnlocks();
			break;
		case Session.GAME_MODE_FIB:
			checkFibModeUnlocks();
			break;
		}
	}

	/**
	 * Checks and unlocks any game play counts based achievements
	 */
	public void checkForGameCountUnlocks() {
		playService
				.incrementAchievement(R.string.achievement_getting_started__level_1);
		playService
				.incrementAchievement(R.string.achievement_getting_started__level_2);
		playService
				.incrementAchievement(R.string.achievement_getting_started__level_3);

		playService
				.incrementAchievement(R.string.achievement_getting_addicted__level_1);
		playService
				.incrementAchievement(R.string.achievement_getting_addicted__level_2);
		playService
				.incrementAchievement(R.string.achievement_getting_addicted__level_3);

		playService
				.incrementAchievement(R.string.achievement_addicted__level_1);
		playService
				.incrementAchievement(R.string.achievement_addicted__level_2);
		playService
				.incrementAchievement(R.string.achievement_addicted__level_3);

		playService.incrementAchievement(R.string.achievement_livein__level_1);
		playService.incrementAchievement(R.string.achievement_livein__level_2);
		playService.incrementAchievement(R.string.achievement_livein__level_3);

		playService.incrementAchievement(R.string.achievement_no_life__level_1);
		playService.incrementAchievement(R.string.achievement_no_life__level_2);
		playService.incrementAchievement(R.string.achievement_no_life__level_3);

		switch (Session.gameMode) {
		case Session.GAME_MODE_DEC:
			playService
					.incrementAchievement(R.string.achievement_layman__level_1);
			playService
					.incrementAchievement(R.string.achievement_layman__level_2);
			playService
					.incrementAchievement(R.string.achievement_layman__level_3);
			playService
					.incrementAchievement(R.string.achievement_layman__level_4);
			playService
					.incrementAchievement(R.string.achievement_layman__level_5);
			playService
					.incrementAchievement(R.string.achievement_layman__level_6);
			playService.incrementAchievement(R.string.achievement_feynman);

			break;
		case Session.GAME_MODE_BIN:
			playService
					.incrementAchievement(R.string.achievement_geek__level_1);
			playService
					.incrementAchievement(R.string.achievement_geek__level_2);
			playService
					.incrementAchievement(R.string.achievement_geek__level_3);
			playService
					.incrementAchievement(R.string.achievement_geek__level_4);
			playService
					.incrementAchievement(R.string.achievement_geek__level_5);
			playService
					.incrementAchievement(R.string.achievement_geek__level_6);
			playService.incrementAchievement(R.string.achievement_bill);
			break;
		case Session.GAME_MODE_HEX:
			playService
					.incrementAchievement(R.string.achievement_coder__level_1);
			playService
					.incrementAchievement(R.string.achievement_coder__level_2);
			playService
					.incrementAchievement(R.string.achievement_coder__level_3);
			playService
					.incrementAchievement(R.string.achievement_coder__level_4);
			playService
					.incrementAchievement(R.string.achievement_coder__level_5);
			playService
					.incrementAchievement(R.string.achievement_coder__level_6);
			playService.incrementAchievement(R.string.achievement_zuck);
			break;
		case Session.GAME_MODE_FIB:
			playService
					.incrementAchievement(R.string.achievement_fibonacci__level_1);
			playService
					.incrementAchievement(R.string.achievement_fibonacci__level_2);
			playService
					.incrementAchievement(R.string.achievement_fibonacci__level_3);
			playService
					.incrementAchievement(R.string.achievement_fibonacci__level_4);
			playService
					.incrementAchievement(R.string.achievement_fibonacci__level_5);
			playService
					.incrementAchievement(R.string.achievement_you_can_be_fibonacci);
			break;
		}

		/**
		 * Game streak unlocks
		 */
		checkStreakUnlocks();
	}

	private void checkBinModeUnlocks() {
		switch (Session.score()) {
		case BIN_COLLEGE:
			playService.unLockAchievement(R.string.achievement_college);
			break;
		case BIN_STUDIOUS:
			playService.unLockAchievement(R.string.achievement_studious);
			break;
		case BIN_GEEK:
			playService.unLockAchievement(R.string.achievement_geek);
			break;
		case BIN_NERD:
			playService.unLockAchievement(R.string.achievement_nerd);
			break;
		case BIN_NERDY_GEEK:
			playService.unLockAchievement(R.string.achievement_the_nerdy_geek);
			break;
		}
	}

	private void checkDecModeUnlocks() {
		switch (Session.score()) {
		case DEC_NOOB:
			playService.unLockAchievement(R.string.achievement_noob);
			break;
		case DEC_BEGINNER:
			playService.unLockAchievement(R.string.achievement_begginer);
			break;
		case DEC_QUICKY:
			playService.unLockAchievement(R.string.achievement_quicky);
			break;
		case DEC_PRO:
			playService.unLockAchievement(R.string.achievement_pro);
			break;
		case DEC_NINJA:
			playService.unLockAchievement(R.string.achievement_ninja);
			break;
		}
	}

	private void checkHexModeUnlocks() {
		switch (Session.score()) {
		case HEX_COPYPASTER:
			playService.unLockAchievement(R.string.achievement_copy_paster);
			break;
		case HEX_GET_IT_DONE:
			playService.unLockAchievement(R.string.achievement_get_it_done);
			break;
		case HEX_CODER:
			playService.unLockAchievement(R.string.achievement_coder);
			break;
		case HEX_PRO_CODER:
			playService.unLockAchievement(R.string.achievement_pro_coder);
			break;
		case HEX_NINJA_PROG:
			playService
					.unLockAchievement(R.string.achievement_ninja_programmer);
			break;
		case HEX_LINUS:
			playService.unLockAchievement(R.string.achievement_linus_torvalds);
			break;
		}
	}

	private void checkFibModeUnlocks() {
		switch (Session.score()) {
		case FIB_OH_FIBONNACI:
			playService.unLockAchievement(R.string.achievement_oh_fibonnaci);
			break;
		case FIB_FIBONNACI_50:
			playService.unLockAchievement(R.string.achievement_fibonacci_50);
			break;
		case FIB_FIBONNACI_75:
			playService.unLockAchievement(R.string.achievement_fibonacci_75);
			break;
		case FIB_FIBONNACI_CENTURY:
			playService
					.unLockAchievement(R.string.achievement_fibonacci_century);
			break;
		case FIB_FIBONNACI_150:
			playService.unLockAchievement(R.string.achievement_fibonacci_150);
			break;
		case FIB_LENARDO_FIBONNACI:
			playService
					.unLockAchievement(R.string.achievement_leonardo_fibonacci);
			break;
		}
	}

	private void checkStreakUnlocks() {
		Calendar c = Calendar.getInstance();
		int nowDay = c.get(Calendar.DATE);
		int nowMonth = c.get(Calendar.MONTH);
		int nowYear = c.get(Calendar.YEAR);
		Log.d("Debug", "Now is : " + c.getTimeInMillis());

		c.setTimeInMillis(db.getLastPlayedTime());
		int lastDay = c.get(Calendar.DATE);
		int lastMonth = c.get(Calendar.MONTH);
		int lastYear = c.get(Calendar.YEAR);

		// Last played is today? Do nothing
		if (nowDay == lastDay && nowMonth == lastMonth && nowYear == lastYear) {
			Log.d(DEBUG_TAG, "Streak unchanged");
			db.setLastPlayedTime(); // This is to initialize last played
			return;
		}

		/**
		 * We need to check if last played is yesterday or some other day. Add
		 * 24 hours to last played. If the dates match - yesterday.
		 */
		c.setTimeInMillis(db.getLastPlayedTime() + 24 * 60 * 60 * 1000);
		lastDay = c.get(Calendar.DATE);
		lastMonth = c.get(Calendar.MONTH);
		lastYear = c.get(Calendar.YEAR);
		if (nowDay == lastDay && nowMonth == lastMonth && nowYear == lastYear) {
			Log.d(DEBUG_TAG, "Streak incremented " + db.getCurrentStreak());
			db.incrementCurrentStreak();
			db.setLastPlayedTime();
			switch (db.getCurrentStreak()) {
			case 3:
				playService.unLockAchievement(R.string.achievement_visitor);
				break;
			case 5:
				playService.unLockAchievement(R.string.achievement_regular);
				break;
			case 7:
				playService
						.unLockAchievement(R.string.achievement_frequent_flyer);
				break;
			case 10:
				playService
						.unLockAchievement(R.string.achievement_bronze_card_holder);
				break;
			case 15:
				playService
						.unLockAchievement(R.string.achievement_silver_card_holder);
				break;
			case 20:
				playService
						.unLockAchievement(R.string.achievement_gold_card_holder);
				break;
			case 25:
				playService
						.unLockAchievement(R.string.achievement_platinum_card_holder);
				break;
			case 30:
				playService.unLockAchievement(R.string.achievement_super_user);
				break;
			}
			return;
		} else {
			Log.d(DEBUG_TAG, "Streak reset");
			db.setLastPlayedTime();
			db.resetCurrentStreak();
		}
	}

}
