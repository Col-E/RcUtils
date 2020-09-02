package me.coley.recaf.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;

/**
 * Proxy to intercept tinylog logging.
 *
 * @author Matt
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Log {
	public static final String APP_LOGGER = "recaf-logger";
	public static final Logger appLogger = LoggerFactory.getLogger(APP_LOGGER);
	/**
	 * Set of consumers that are fed trace-level messages.
	 */
	public static final Set<Consumer<String>> traceConsumers = new HashSet<>();
	/**
	 * Set of consumers that are fed debug-level messages.
	 */
	public static final Set<Consumer<String>> debugConsumers = new HashSet<>();
	/**
	 * Set of consumers that are fed info-level messages.
	 */
	public static final Set<Consumer<String>> infoConsumers = new HashSet<>();
	/**
	 * Set of consumers that are fed warn-level messages.
	 */
	public static final Set<Consumer<String>> warnConsumers = new HashSet<>();

	/**
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void trace(String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.trace(msgCmp);
		traceConsumers.forEach(c -> c.accept(msgCmp));
	}

	/**
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void debug(String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.debug(msgCmp);
		debugConsumers.forEach(c -> c.accept(msgCmp));
	}

	/**
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void info(String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.info(msgCmp);
		infoConsumers.forEach(c -> c.accept(msgCmp));
	}

	/**
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void warn(String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.warn(msgCmp);
		warnConsumers.forEach(c -> c.accept(msgCmp));
	}

	/**
	 * @param t
	 * 		Exception to print.
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void warn(Throwable t, String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.warn(msgCmp, t);
		warnConsumers.forEach(c -> c.accept(msgCmp));
	}

	/**
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void error(String msg, Object... args) {
		error(null, msg, args);
	}

	/**
	 * @param t
	 * 		Exception to print.
	 * @param msg
	 * 		Message format.
	 * @param args
	 * 		Message arguments.
	 */
	public static void error(Throwable t, String msg, Object... args) {
		String msgCmp = compile(msg,args);
		appLogger.error(msgCmp, t);
	}

	/**
	 * Compiles message with "{}" arg patterns.
	 *
	 * @param msg
	 * 		Message pattern.
	 * @param args
	 * 		Values to pass.
	 *
	 * @return Compiled message with inlined arg values.
	 */
	private static String compile(String msg, Object[] args) {
		int c = 0;
		while(msg.contains("{}")) {
			// Failsafe, shouldn't occur if logging is written correctly
			if (c == args.length) 
				return msg;
			// Replace arg in pattern
			Object arg = args[c];
			String argStr = arg == null ? "null" : arg.toString();
			msg = msg.replaceFirst("\\{}", Matcher.quoteReplacement(argStr));
			c++;
		}
		return msg;
	}
}
