package com.example.toyrobot.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.toyrobot.model.FacingEnum;
import com.example.toyrobot.model.Robot;

/**
 * Robot main controller
 * 
 * @author <a href="mailto:mokhlisse_badre@yahoo.fr">Badre-Edine Mokhlisse</a>
 *
 */
@RestController
public class RobotController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotController.class);
	private static final Pattern PLACE_PATTERN = Pattern.compile("PLACE (\\d),(\\d),(NORTH|SOUTH|EAST|WEST)");

	/**
	 * main controller
	 * 
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String readCommand(@RequestBody String command) {

		LOGGER.info("processing command" + command + " ...");

		Robot robot = new Robot();
		String[] lines = command.split("\\r?\\n");
		for (String line : lines) {
			line = line.trim();
			try {
				switch (line) {
				case "MOVE":
					robot.move();
					break;
				case "LEFT":
					robot.left();
					break;
				case "RIGHT":
					robot.right();
					break;
				case "REPORT":
					return robot.report();
				default:
					place(robot, line);
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error("ignore command: " + command);
			}
		}

		return null;
	}

	/**
	 * places robot in the new position given by command
	 * 
	 * @param robot
	 *            robot to place
	 * @param command
	 *            contains new position to move robot to
	 */
	private void place(Robot robot, String command) {
		Matcher m = PLACE_PATTERN.matcher(command);
		if (m.matches()) {
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			FacingEnum facingEnum = FacingEnum.valueOf(m.group(3));
			robot.place(x, y, facingEnum);
		} else {
			LOGGER.error("ignore un-matching place command: " + command);
		}
	}

}