<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8/jdk8/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8/jdk8/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8/jdk8/jdk/static/mercurial.js"></script>

<title>jdk8/jdk8/jdk: 75d8c1200df8 src/share/classes/sun/util/logging/PlatformLogger.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8/jdk8/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8/jdk8/jdk/shortlog/75d8c1200df8">log</a></li>
<li><a href="/jdk8/jdk8/jdk/graph/75d8c1200df8">graph</a></li>
<li><a href="/jdk8/jdk8/jdk/tags">tags</a></li>
<li><a href="/jdk8/jdk8/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8/jdk8/jdk/rev/75d8c1200df8">changeset</a></li>
<li><a href="/jdk8/jdk8/jdk/file/75d8c1200df8/src/share/classes/sun/util/logging/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8/jdk8/jdk/file/tip/src/share/classes/sun/util/logging/PlatformLogger.java">latest</a></li>
<li><a href="/jdk8/jdk8/jdk/diff/75d8c1200df8/src/share/classes/sun/util/logging/PlatformLogger.java">diff</a></li>
<li><a href="/jdk8/jdk8/jdk/comparison/75d8c1200df8/src/share/classes/sun/util/logging/PlatformLogger.java">comparison</a></li>
<li><a href="/jdk8/jdk8/jdk/annotate/75d8c1200df8/src/share/classes/sun/util/logging/PlatformLogger.java">annotate</a></li>
<li><a href="/jdk8/jdk8/jdk/log/75d8c1200df8/src/share/classes/sun/util/logging/PlatformLogger.java">file log</a></li>
<li><a href="/jdk8/jdk8/jdk/raw-file/75d8c1200df8/src/share/classes/sun/util/logging/PlatformLogger.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8/jdk8/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8">jdk8</a> / <a href="/jdk8/jdk8">jdk8</a> / <a href="/jdk8/jdk8/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/util/logging/PlatformLogger.java @ 3615:75d8c1200df8</h3>

<form class="search" action="/jdk8/jdk8/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8/jdk8/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">7020522: Need to reapply the fix for 6664512
Reviewed-by: art, mchung</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#100;&#99;&#104;&#101;&#114;&#101;&#112;&#97;&#110;&#111;&#118;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Fri, 25 Feb 2011 13:58:54 +0300</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8/jdk8/jdk/file/58aa8eadae5f/src/share/classes/sun/util/logging/PlatformLogger.java">58aa8eadae5f</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"><a href="/jdk8/jdk8/jdk/file/d2bbdd709307/src/share/classes/sun/util/logging/PlatformLogger.java">d2bbdd709307</a> <a href="/jdk8/jdk8/jdk/file/272483f6650b/src/share/classes/sun/util/logging/PlatformLogger.java">272483f6650b</a> </td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 2009, 2010, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
<span id="l3"> * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.</span><a href="#l3"></a>
<span id="l4"> *</span><a href="#l4"></a>
<span id="l5"> * This code is free software; you can redistribute it and/or modify it</span><a href="#l5"></a>
<span id="l6"> * under the terms of the GNU General Public License version 2 only, as</span><a href="#l6"></a>
<span id="l7"> * published by the Free Software Foundation.  Oracle designates this</span><a href="#l7"></a>
<span id="l8"> * particular file as subject to the &quot;Classpath&quot; exception as provided</span><a href="#l8"></a>
<span id="l9"> * by Oracle in the LICENSE file that accompanied this code.</span><a href="#l9"></a>
<span id="l10"> *</span><a href="#l10"></a>
<span id="l11"> * This code is distributed in the hope that it will be useful, but WITHOUT</span><a href="#l11"></a>
<span id="l12"> * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or</span><a href="#l12"></a>
<span id="l13"> * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License</span><a href="#l13"></a>
<span id="l14"> * version 2 for more details (a copy is included in the LICENSE file that</span><a href="#l14"></a>
<span id="l15"> * accompanied this code).</span><a href="#l15"></a>
<span id="l16"> *</span><a href="#l16"></a>
<span id="l17"> * You should have received a copy of the GNU General Public License version</span><a href="#l17"></a>
<span id="l18"> * 2 along with this work; if not, write to the Free Software Foundation,</span><a href="#l18"></a>
<span id="l19"> * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.</span><a href="#l19"></a>
<span id="l20"> *</span><a href="#l20"></a>
<span id="l21"> * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA</span><a href="#l21"></a>
<span id="l22"> * or visit www.oracle.com if you need additional information or have any</span><a href="#l22"></a>
<span id="l23"> * questions.</span><a href="#l23"></a>
<span id="l24"> */</span><a href="#l24"></a>
<span id="l25"></span><a href="#l25"></a>
<span id="l26"></span><a href="#l26"></a>
<span id="l27">package sun.util.logging;</span><a href="#l27"></a>
<span id="l28"></span><a href="#l28"></a>
<span id="l29">import java.lang.ref.WeakReference;</span><a href="#l29"></a>
<span id="l30">import java.lang.reflect.InvocationTargetException;</span><a href="#l30"></a>
<span id="l31">import java.lang.reflect.Method;</span><a href="#l31"></a>
<span id="l32">import java.io.PrintStream;</span><a href="#l32"></a>
<span id="l33">import java.io.PrintWriter;</span><a href="#l33"></a>
<span id="l34">import java.io.StringWriter;</span><a href="#l34"></a>
<span id="l35">import java.security.AccessController;</span><a href="#l35"></a>
<span id="l36">import java.security.PrivilegedAction;</span><a href="#l36"></a>
<span id="l37">import java.text.MessageFormat;</span><a href="#l37"></a>
<span id="l38">import java.util.Date;</span><a href="#l38"></a>
<span id="l39">import java.util.HashMap;</span><a href="#l39"></a>
<span id="l40">import java.util.Map;</span><a href="#l40"></a>
<span id="l41">import sun.misc.JavaLangAccess;</span><a href="#l41"></a>
<span id="l42">import sun.misc.SharedSecrets;</span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">/**</span><a href="#l44"></a>
<span id="l45"> * Platform logger provides an API for the JRE components to log</span><a href="#l45"></a>
<span id="l46"> * messages.  This enables the runtime components to eliminate the</span><a href="#l46"></a>
<span id="l47"> * static dependency of the logging facility and also defers the</span><a href="#l47"></a>
<span id="l48"> * java.util.logging initialization until it is enabled.</span><a href="#l48"></a>
<span id="l49"> * In addition, the PlatformLogger API can be used if the logging</span><a href="#l49"></a>
<span id="l50"> * module does not exist.</span><a href="#l50"></a>
<span id="l51"> *</span><a href="#l51"></a>
<span id="l52"> * If the logging facility is not enabled, the platform loggers</span><a href="#l52"></a>
<span id="l53"> * will output log messages per the default logging configuration</span><a href="#l53"></a>
<span id="l54"> * (see below). In this implementation, it does not log the</span><a href="#l54"></a>
<span id="l55"> * the stack frame information issuing the log message.</span><a href="#l55"></a>
<span id="l56"> *</span><a href="#l56"></a>
<span id="l57"> * When the logging facility is enabled (at startup or runtime),</span><a href="#l57"></a>
<span id="l58"> * the java.util.logging.Logger will be created for each platform</span><a href="#l58"></a>
<span id="l59"> * logger and all log messages will be forwarded to the Logger</span><a href="#l59"></a>
<span id="l60"> * to handle.</span><a href="#l60"></a>
<span id="l61"> *</span><a href="#l61"></a>
<span id="l62"> * Logging facility is &quot;enabled&quot; when one of the following</span><a href="#l62"></a>
<span id="l63"> * conditions is met:</span><a href="#l63"></a>
<span id="l64"> * 1) a system property &quot;java.util.logging.config.class&quot; or</span><a href="#l64"></a>
<span id="l65"> *    &quot;java.util.logging.config.file&quot; is set</span><a href="#l65"></a>
<span id="l66"> * 2) java.util.logging.LogManager or java.util.logging.Logger</span><a href="#l66"></a>
<span id="l67"> *    is referenced that will trigger the logging initialization.</span><a href="#l67"></a>
<span id="l68"> *</span><a href="#l68"></a>
<span id="l69"> * Default logging configuration:</span><a href="#l69"></a>
<span id="l70"> *   global logging level = INFO</span><a href="#l70"></a>
<span id="l71"> *   handlers = java.util.logging.ConsoleHandler</span><a href="#l71"></a>
<span id="l72"> *   java.util.logging.ConsoleHandler.level = INFO</span><a href="#l72"></a>
<span id="l73"> *   java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter</span><a href="#l73"></a>
<span id="l74"> *</span><a href="#l74"></a>
<span id="l75"> * Limitation:</span><a href="#l75"></a>
<span id="l76"> * &lt;JAVA_HOME&gt;/lib/logging.properties is the system-wide logging</span><a href="#l76"></a>
<span id="l77"> * configuration defined in the specification and read in the</span><a href="#l77"></a>
<span id="l78"> * default case to configure any java.util.logging.Logger instances.</span><a href="#l78"></a>
<span id="l79"> * Platform loggers will not detect if &lt;JAVA_HOME&gt;/lib/logging.properties</span><a href="#l79"></a>
<span id="l80"> * is modified. In other words, unless the java.util.logging API</span><a href="#l80"></a>
<span id="l81"> * is used at runtime or the logging system properties is set,</span><a href="#l81"></a>
<span id="l82"> * the platform loggers will use the default setting described above.</span><a href="#l82"></a>
<span id="l83"> * The platform loggers are designed for JDK developers use and</span><a href="#l83"></a>
<span id="l84"> * this limitation can be workaround with setting</span><a href="#l84"></a>
<span id="l85"> * -Djava.util.logging.config.file system property.</span><a href="#l85"></a>
<span id="l86"> *</span><a href="#l86"></a>
<span id="l87"> * @since 1.7</span><a href="#l87"></a>
<span id="l88"> */</span><a href="#l88"></a>
<span id="l89">public class PlatformLogger {</span><a href="#l89"></a>
<span id="l90">    // Same values as java.util.logging.Level for easy mapping</span><a href="#l90"></a>
<span id="l91">    public static final int OFF     = Integer.MAX_VALUE;</span><a href="#l91"></a>
<span id="l92">    public static final int SEVERE  = 1000;</span><a href="#l92"></a>
<span id="l93">    public static final int WARNING = 900;</span><a href="#l93"></a>
<span id="l94">    public static final int INFO    = 800;</span><a href="#l94"></a>
<span id="l95">    public static final int CONFIG  = 700;</span><a href="#l95"></a>
<span id="l96">    public static final int FINE    = 500;</span><a href="#l96"></a>
<span id="l97">    public static final int FINER   = 400;</span><a href="#l97"></a>
<span id="l98">    public static final int FINEST  = 300;</span><a href="#l98"></a>
<span id="l99">    public static final int ALL     = Integer.MIN_VALUE;</span><a href="#l99"></a>
<span id="l100"></span><a href="#l100"></a>
<span id="l101">    private static final int defaultLevel = INFO;</span><a href="#l101"></a>
<span id="l102">    private static boolean loggingEnabled;</span><a href="#l102"></a>
<span id="l103">    static {</span><a href="#l103"></a>
<span id="l104">        loggingEnabled = AccessController.doPrivileged(</span><a href="#l104"></a>
<span id="l105">            new PrivilegedAction&lt;Boolean&gt;() {</span><a href="#l105"></a>
<span id="l106">                public Boolean run() {</span><a href="#l106"></a>
<span id="l107">                    String cname = System.getProperty(&quot;java.util.logging.config.class&quot;);</span><a href="#l107"></a>
<span id="l108">                    String fname = System.getProperty(&quot;java.util.logging.config.file&quot;);</span><a href="#l108"></a>
<span id="l109">                    return (cname != null || fname != null);</span><a href="#l109"></a>
<span id="l110">                }</span><a href="#l110"></a>
<span id="l111">            });</span><a href="#l111"></a>
<span id="l112">    }</span><a href="#l112"></a>
<span id="l113"></span><a href="#l113"></a>
<span id="l114">    // Table of known loggers.  Maps names to PlatformLoggers.</span><a href="#l114"></a>
<span id="l115">    private static Map&lt;String,WeakReference&lt;PlatformLogger&gt;&gt; loggers =</span><a href="#l115"></a>
<span id="l116">        new HashMap&lt;&gt;();</span><a href="#l116"></a>
<span id="l117"></span><a href="#l117"></a>
<span id="l118">    /**</span><a href="#l118"></a>
<span id="l119">     * Returns a PlatformLogger of a given name.</span><a href="#l119"></a>
<span id="l120">     */</span><a href="#l120"></a>
<span id="l121">    public static synchronized PlatformLogger getLogger(String name) {</span><a href="#l121"></a>
<span id="l122">        PlatformLogger log = null;</span><a href="#l122"></a>
<span id="l123">        WeakReference&lt;PlatformLogger&gt; ref = loggers.get(name);</span><a href="#l123"></a>
<span id="l124">        if (ref != null) {</span><a href="#l124"></a>
<span id="l125">            log = ref.get();</span><a href="#l125"></a>
<span id="l126">        }</span><a href="#l126"></a>
<span id="l127">        if (log == null) {</span><a href="#l127"></a>
<span id="l128">            log = new PlatformLogger(name);</span><a href="#l128"></a>
<span id="l129">            loggers.put(name, new WeakReference&lt;&gt;(log));</span><a href="#l129"></a>
<span id="l130">        }</span><a href="#l130"></a>
<span id="l131">        return log;</span><a href="#l131"></a>
<span id="l132">    }</span><a href="#l132"></a>
<span id="l133"></span><a href="#l133"></a>
<span id="l134">    /**</span><a href="#l134"></a>
<span id="l135">     * Initialize java.util.logging.Logger objects for all platform loggers.</span><a href="#l135"></a>
<span id="l136">     * This method is called from LogManager.readPrimordialConfiguration().</span><a href="#l136"></a>
<span id="l137">     */</span><a href="#l137"></a>
<span id="l138">    public static synchronized void redirectPlatformLoggers() {</span><a href="#l138"></a>
<span id="l139">        if (loggingEnabled || !LoggingSupport.isAvailable()) return;</span><a href="#l139"></a>
<span id="l140"></span><a href="#l140"></a>
<span id="l141">        loggingEnabled = true;</span><a href="#l141"></a>
<span id="l142">        for (Map.Entry&lt;String, WeakReference&lt;PlatformLogger&gt;&gt; entry : loggers.entrySet()) {</span><a href="#l142"></a>
<span id="l143">            WeakReference&lt;PlatformLogger&gt; ref = entry.getValue();</span><a href="#l143"></a>
<span id="l144">            PlatformLogger plog = ref.get();</span><a href="#l144"></a>
<span id="l145">            if (plog != null) {</span><a href="#l145"></a>
<span id="l146">                plog.newJavaLogger();</span><a href="#l146"></a>
<span id="l147">            }</span><a href="#l147"></a>
<span id="l148">        }</span><a href="#l148"></a>
<span id="l149">    }</span><a href="#l149"></a>
<span id="l150"></span><a href="#l150"></a>
<span id="l151">    /**</span><a href="#l151"></a>
<span id="l152">     * Creates a new JavaLogger that the platform logger uses</span><a href="#l152"></a>
<span id="l153">     */</span><a href="#l153"></a>
<span id="l154">    private void newJavaLogger() {</span><a href="#l154"></a>
<span id="l155">        logger = new JavaLogger(logger.name, logger.effectiveLevel);</span><a href="#l155"></a>
<span id="l156">    }</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">    // logger may be replaced with a JavaLogger object</span><a href="#l158"></a>
<span id="l159">    // when the logging facility is enabled</span><a href="#l159"></a>
<span id="l160">    private volatile LoggerProxy logger;</span><a href="#l160"></a>
<span id="l161"></span><a href="#l161"></a>
<span id="l162">    private PlatformLogger(String name) {</span><a href="#l162"></a>
<span id="l163">        if (loggingEnabled) {</span><a href="#l163"></a>
<span id="l164">            this.logger = new JavaLogger(name);</span><a href="#l164"></a>
<span id="l165">        } else {</span><a href="#l165"></a>
<span id="l166">            this.logger = new LoggerProxy(name);</span><a href="#l166"></a>
<span id="l167">        }</span><a href="#l167"></a>
<span id="l168">    }</span><a href="#l168"></a>
<span id="l169"></span><a href="#l169"></a>
<span id="l170">    /**</span><a href="#l170"></a>
<span id="l171">     * A convenience method to test if the logger is turned off.</span><a href="#l171"></a>
<span id="l172">     * (i.e. its level is OFF).</span><a href="#l172"></a>
<span id="l173">     */</span><a href="#l173"></a>
<span id="l174">    public boolean isEnabled() {</span><a href="#l174"></a>
<span id="l175">        return logger.isEnabled();</span><a href="#l175"></a>
<span id="l176">    }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    /**</span><a href="#l178"></a>
<span id="l179">     * Gets the name for this platform logger.</span><a href="#l179"></a>
<span id="l180">     */</span><a href="#l180"></a>
<span id="l181">    public String getName() {</span><a href="#l181"></a>
<span id="l182">        return logger.name;</span><a href="#l182"></a>
<span id="l183">    }</span><a href="#l183"></a>
<span id="l184"></span><a href="#l184"></a>
<span id="l185">    /**</span><a href="#l185"></a>
<span id="l186">     * Returns true if a message of the given level would actually</span><a href="#l186"></a>
<span id="l187">     * be logged by this logger.</span><a href="#l187"></a>
<span id="l188">     */</span><a href="#l188"></a>
<span id="l189">    public boolean isLoggable(int level) {</span><a href="#l189"></a>
<span id="l190">        return logger.isLoggable(level);</span><a href="#l190"></a>
<span id="l191">    }</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    /**</span><a href="#l193"></a>
<span id="l194">     * Gets the current log level.  Returns 0 if the current effective level</span><a href="#l194"></a>
<span id="l195">     * is not set (equivalent to Logger.getLevel() returns null).</span><a href="#l195"></a>
<span id="l196">     */</span><a href="#l196"></a>
<span id="l197">    public int getLevel() {</span><a href="#l197"></a>
<span id="l198">        return logger.getLevel();</span><a href="#l198"></a>
<span id="l199">    }</span><a href="#l199"></a>
<span id="l200"></span><a href="#l200"></a>
<span id="l201">    /**</span><a href="#l201"></a>
<span id="l202">     * Sets the log level.</span><a href="#l202"></a>
<span id="l203">     */</span><a href="#l203"></a>
<span id="l204">    public void setLevel(int newLevel) {</span><a href="#l204"></a>
<span id="l205">        logger.setLevel(newLevel);</span><a href="#l205"></a>
<span id="l206">    }</span><a href="#l206"></a>
<span id="l207"></span><a href="#l207"></a>
<span id="l208">    /**</span><a href="#l208"></a>
<span id="l209">     * Logs a SEVERE message.</span><a href="#l209"></a>
<span id="l210">     */</span><a href="#l210"></a>
<span id="l211">    public void severe(String msg) {</span><a href="#l211"></a>
<span id="l212">        logger.doLog(SEVERE, msg);</span><a href="#l212"></a>
<span id="l213">    }</span><a href="#l213"></a>
<span id="l214"></span><a href="#l214"></a>
<span id="l215">    public void severe(String msg, Throwable t) {</span><a href="#l215"></a>
<span id="l216">        logger.doLog(SEVERE, msg, t);</span><a href="#l216"></a>
<span id="l217">    }</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">    public void severe(String msg, Object... params) {</span><a href="#l219"></a>
<span id="l220">        logger.doLog(SEVERE, msg, params);</span><a href="#l220"></a>
<span id="l221">    }</span><a href="#l221"></a>
<span id="l222"></span><a href="#l222"></a>
<span id="l223">    /**</span><a href="#l223"></a>
<span id="l224">     * Logs a WARNING message.</span><a href="#l224"></a>
<span id="l225">     */</span><a href="#l225"></a>
<span id="l226">    public void warning(String msg) {</span><a href="#l226"></a>
<span id="l227">        logger.doLog(WARNING, msg);</span><a href="#l227"></a>
<span id="l228">    }</span><a href="#l228"></a>
<span id="l229"></span><a href="#l229"></a>
<span id="l230">    public void warning(String msg, Throwable t) {</span><a href="#l230"></a>
<span id="l231">        logger.doLog(WARNING, msg, t);</span><a href="#l231"></a>
<span id="l232">    }</span><a href="#l232"></a>
<span id="l233"></span><a href="#l233"></a>
<span id="l234">    public void warning(String msg, Object... params) {</span><a href="#l234"></a>
<span id="l235">        logger.doLog(WARNING, msg, params);</span><a href="#l235"></a>
<span id="l236">    }</span><a href="#l236"></a>
<span id="l237"></span><a href="#l237"></a>
<span id="l238">    /**</span><a href="#l238"></a>
<span id="l239">     * Logs an INFO message.</span><a href="#l239"></a>
<span id="l240">     */</span><a href="#l240"></a>
<span id="l241">    public void info(String msg) {</span><a href="#l241"></a>
<span id="l242">        logger.doLog(INFO, msg);</span><a href="#l242"></a>
<span id="l243">    }</span><a href="#l243"></a>
<span id="l244"></span><a href="#l244"></a>
<span id="l245">    public void info(String msg, Throwable t) {</span><a href="#l245"></a>
<span id="l246">        logger.doLog(INFO, msg, t);</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    public void info(String msg, Object... params) {</span><a href="#l249"></a>
<span id="l250">        logger.doLog(INFO, msg, params);</span><a href="#l250"></a>
<span id="l251">    }</span><a href="#l251"></a>
<span id="l252"></span><a href="#l252"></a>
<span id="l253">    /**</span><a href="#l253"></a>
<span id="l254">     * Logs a CONFIG message.</span><a href="#l254"></a>
<span id="l255">     */</span><a href="#l255"></a>
<span id="l256">    public void config(String msg) {</span><a href="#l256"></a>
<span id="l257">        logger.doLog(CONFIG, msg);</span><a href="#l257"></a>
<span id="l258">    }</span><a href="#l258"></a>
<span id="l259"></span><a href="#l259"></a>
<span id="l260">    public void config(String msg, Throwable t) {</span><a href="#l260"></a>
<span id="l261">        logger.doLog(CONFIG, msg, t);</span><a href="#l261"></a>
<span id="l262">    }</span><a href="#l262"></a>
<span id="l263"></span><a href="#l263"></a>
<span id="l264">    public void config(String msg, Object... params) {</span><a href="#l264"></a>
<span id="l265">        logger.doLog(CONFIG, msg, params);</span><a href="#l265"></a>
<span id="l266">    }</span><a href="#l266"></a>
<span id="l267"></span><a href="#l267"></a>
<span id="l268">    /**</span><a href="#l268"></a>
<span id="l269">     * Logs a FINE message.</span><a href="#l269"></a>
<span id="l270">     */</span><a href="#l270"></a>
<span id="l271">    public void fine(String msg) {</span><a href="#l271"></a>
<span id="l272">        logger.doLog(FINE, msg);</span><a href="#l272"></a>
<span id="l273">    }</span><a href="#l273"></a>
<span id="l274"></span><a href="#l274"></a>
<span id="l275">    public void fine(String msg, Throwable t) {</span><a href="#l275"></a>
<span id="l276">        logger.doLog(FINE, msg, t);</span><a href="#l276"></a>
<span id="l277">    }</span><a href="#l277"></a>
<span id="l278"></span><a href="#l278"></a>
<span id="l279">    public void fine(String msg, Object... params) {</span><a href="#l279"></a>
<span id="l280">        logger.doLog(FINE, msg, params);</span><a href="#l280"></a>
<span id="l281">    }</span><a href="#l281"></a>
<span id="l282"></span><a href="#l282"></a>
<span id="l283">    /**</span><a href="#l283"></a>
<span id="l284">     * Logs a FINER message.</span><a href="#l284"></a>
<span id="l285">     */</span><a href="#l285"></a>
<span id="l286">    public void finer(String msg) {</span><a href="#l286"></a>
<span id="l287">        logger.doLog(FINER, msg);</span><a href="#l287"></a>
<span id="l288">    }</span><a href="#l288"></a>
<span id="l289"></span><a href="#l289"></a>
<span id="l290">    public void finer(String msg, Throwable t) {</span><a href="#l290"></a>
<span id="l291">        logger.doLog(FINER, msg, t);</span><a href="#l291"></a>
<span id="l292">    }</span><a href="#l292"></a>
<span id="l293"></span><a href="#l293"></a>
<span id="l294">    public void finer(String msg, Object... params) {</span><a href="#l294"></a>
<span id="l295">        logger.doLog(FINER, msg, params);</span><a href="#l295"></a>
<span id="l296">    }</span><a href="#l296"></a>
<span id="l297"></span><a href="#l297"></a>
<span id="l298">    /**</span><a href="#l298"></a>
<span id="l299">     * Logs a FINEST message.</span><a href="#l299"></a>
<span id="l300">     */</span><a href="#l300"></a>
<span id="l301">    public void finest(String msg) {</span><a href="#l301"></a>
<span id="l302">        logger.doLog(FINEST, msg);</span><a href="#l302"></a>
<span id="l303">    }</span><a href="#l303"></a>
<span id="l304"></span><a href="#l304"></a>
<span id="l305">    public void finest(String msg, Throwable t) {</span><a href="#l305"></a>
<span id="l306">        logger.doLog(FINEST, msg, t);</span><a href="#l306"></a>
<span id="l307">    }</span><a href="#l307"></a>
<span id="l308"></span><a href="#l308"></a>
<span id="l309">    public void finest(String msg, Object... params) {</span><a href="#l309"></a>
<span id="l310">        logger.doLog(FINEST, msg, params);</span><a href="#l310"></a>
<span id="l311">    }</span><a href="#l311"></a>
<span id="l312"></span><a href="#l312"></a>
<span id="l313">    /**</span><a href="#l313"></a>
<span id="l314">     * Default platform logging support - output messages to</span><a href="#l314"></a>
<span id="l315">     * System.err - equivalent to ConsoleHandler with SimpleFormatter.</span><a href="#l315"></a>
<span id="l316">     */</span><a href="#l316"></a>
<span id="l317">    static class LoggerProxy {</span><a href="#l317"></a>
<span id="l318">        private static final PrintStream defaultStream = System.err;</span><a href="#l318"></a>
<span id="l319">        private static final String lineSeparator = AccessController.doPrivileged(</span><a href="#l319"></a>
<span id="l320">            new PrivilegedAction&lt;String&gt;() {</span><a href="#l320"></a>
<span id="l321">                public String run() {</span><a href="#l321"></a>
<span id="l322">                    return System.getProperty(&quot;line.separator&quot;);</span><a href="#l322"></a>
<span id="l323">                }</span><a href="#l323"></a>
<span id="l324">            });</span><a href="#l324"></a>
<span id="l325"></span><a href="#l325"></a>
<span id="l326">        final String name;</span><a href="#l326"></a>
<span id="l327">        volatile int levelValue;</span><a href="#l327"></a>
<span id="l328">        volatile int effectiveLevel = 0; // current effective level value</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">        LoggerProxy(String name) {</span><a href="#l330"></a>
<span id="l331">            this(name, defaultLevel);</span><a href="#l331"></a>
<span id="l332">        }</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334">        LoggerProxy(String name, int level) {</span><a href="#l334"></a>
<span id="l335">            this.name = name;</span><a href="#l335"></a>
<span id="l336">            this.levelValue = level == 0 ? defaultLevel : level;</span><a href="#l336"></a>
<span id="l337">        }</span><a href="#l337"></a>
<span id="l338"></span><a href="#l338"></a>
<span id="l339">        boolean isEnabled() {</span><a href="#l339"></a>
<span id="l340">            return levelValue != OFF;</span><a href="#l340"></a>
<span id="l341">        }</span><a href="#l341"></a>
<span id="l342"></span><a href="#l342"></a>
<span id="l343">        int getLevel() {</span><a href="#l343"></a>
<span id="l344">            return effectiveLevel;</span><a href="#l344"></a>
<span id="l345">        }</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">        void setLevel(int newLevel) {</span><a href="#l347"></a>
<span id="l348">            levelValue = newLevel;</span><a href="#l348"></a>
<span id="l349">            effectiveLevel = newLevel;</span><a href="#l349"></a>
<span id="l350">        }</span><a href="#l350"></a>
<span id="l351"></span><a href="#l351"></a>
<span id="l352">        void doLog(int level, String msg) {</span><a href="#l352"></a>
<span id="l353">            if (level &lt; levelValue || levelValue == OFF) {</span><a href="#l353"></a>
<span id="l354">                return;</span><a href="#l354"></a>
<span id="l355">            }</span><a href="#l355"></a>
<span id="l356">            defaultStream.println(format(level, msg, null));</span><a href="#l356"></a>
<span id="l357">        }</span><a href="#l357"></a>
<span id="l358"></span><a href="#l358"></a>
<span id="l359">        void doLog(int level, String msg, Throwable thrown) {</span><a href="#l359"></a>
<span id="l360">            if (level &lt; levelValue || levelValue == OFF) {</span><a href="#l360"></a>
<span id="l361">                return;</span><a href="#l361"></a>
<span id="l362">            }</span><a href="#l362"></a>
<span id="l363">            defaultStream.println(format(level, msg, thrown));</span><a href="#l363"></a>
<span id="l364">        }</span><a href="#l364"></a>
<span id="l365"></span><a href="#l365"></a>
<span id="l366">        void doLog(int level, String msg, Object... params) {</span><a href="#l366"></a>
<span id="l367">            if (level &lt; levelValue || levelValue == OFF) {</span><a href="#l367"></a>
<span id="l368">                return;</span><a href="#l368"></a>
<span id="l369">            }</span><a href="#l369"></a>
<span id="l370">            String newMsg = formatMessage(msg, params);</span><a href="#l370"></a>
<span id="l371">            defaultStream.println(format(level, newMsg, null));</span><a href="#l371"></a>
<span id="l372">        }</span><a href="#l372"></a>
<span id="l373"></span><a href="#l373"></a>
<span id="l374">        public boolean isLoggable(int level) {</span><a href="#l374"></a>
<span id="l375">            if (level &lt; levelValue || levelValue == OFF) {</span><a href="#l375"></a>
<span id="l376">                return false;</span><a href="#l376"></a>
<span id="l377">            }</span><a href="#l377"></a>
<span id="l378">            return true;</span><a href="#l378"></a>
<span id="l379">        }</span><a href="#l379"></a>
<span id="l380"></span><a href="#l380"></a>
<span id="l381">        private static final String format = &quot;{0,date} {0,time}&quot;;</span><a href="#l381"></a>
<span id="l382"></span><a href="#l382"></a>
<span id="l383">        private Object args[] = new Object[1];</span><a href="#l383"></a>
<span id="l384">        private MessageFormat formatter;</span><a href="#l384"></a>
<span id="l385">        private Date dat;</span><a href="#l385"></a>
<span id="l386"></span><a href="#l386"></a>
<span id="l387">        // Copied from java.util.logging.Formatter.formatMessage</span><a href="#l387"></a>
<span id="l388">        private String formatMessage(String format, Object... parameters) {</span><a href="#l388"></a>
<span id="l389">            // Do the formatting.</span><a href="#l389"></a>
<span id="l390">            try {</span><a href="#l390"></a>
<span id="l391">                if (parameters == null || parameters.length == 0) {</span><a href="#l391"></a>
<span id="l392">                    // No parameters.  Just return format string.</span><a href="#l392"></a>
<span id="l393">                    return format;</span><a href="#l393"></a>
<span id="l394">                }</span><a href="#l394"></a>
<span id="l395">                // Is it a java.text style format?</span><a href="#l395"></a>
<span id="l396">                // Ideally we could match with</span><a href="#l396"></a>
<span id="l397">                // Pattern.compile(&quot;\\{\\d&quot;).matcher(format).find())</span><a href="#l397"></a>
<span id="l398">                // However the cost is 14% higher, so we cheaply check for</span><a href="#l398"></a>
<span id="l399">                // 1 of the first 4 parameters</span><a href="#l399"></a>
<span id="l400">                if (format.indexOf(&quot;{0&quot;) &gt;= 0 || format.indexOf(&quot;{1&quot;) &gt;=0 ||</span><a href="#l400"></a>
<span id="l401">                            format.indexOf(&quot;{2&quot;) &gt;=0|| format.indexOf(&quot;{3&quot;) &gt;=0) {</span><a href="#l401"></a>
<span id="l402">                    return java.text.MessageFormat.format(format, parameters);</span><a href="#l402"></a>
<span id="l403">                }</span><a href="#l403"></a>
<span id="l404">                return format;</span><a href="#l404"></a>
<span id="l405">            } catch (Exception ex) {</span><a href="#l405"></a>
<span id="l406">                // Formatting failed: use format string.</span><a href="#l406"></a>
<span id="l407">                return format;</span><a href="#l407"></a>
<span id="l408">            }</span><a href="#l408"></a>
<span id="l409">        }</span><a href="#l409"></a>
<span id="l410"></span><a href="#l410"></a>
<span id="l411">        private synchronized String format(int level, String msg, Throwable thrown) {</span><a href="#l411"></a>
<span id="l412">            StringBuffer sb = new StringBuffer();</span><a href="#l412"></a>
<span id="l413">            // Minimize memory allocations here.</span><a href="#l413"></a>
<span id="l414">            if (dat == null) {</span><a href="#l414"></a>
<span id="l415">                dat = new Date();</span><a href="#l415"></a>
<span id="l416">                formatter = new MessageFormat(format);</span><a href="#l416"></a>
<span id="l417">            }</span><a href="#l417"></a>
<span id="l418">            dat.setTime(System.currentTimeMillis());</span><a href="#l418"></a>
<span id="l419">            args[0] = dat;</span><a href="#l419"></a>
<span id="l420">            StringBuffer text = new StringBuffer();</span><a href="#l420"></a>
<span id="l421">            formatter.format(args, text, null);</span><a href="#l421"></a>
<span id="l422">            sb.append(text);</span><a href="#l422"></a>
<span id="l423">            sb.append(&quot; &quot;);</span><a href="#l423"></a>
<span id="l424">            sb.append(getCallerInfo());</span><a href="#l424"></a>
<span id="l425">            sb.append(lineSeparator);</span><a href="#l425"></a>
<span id="l426">            sb.append(PlatformLogger.getLevelName(level));</span><a href="#l426"></a>
<span id="l427">            sb.append(&quot;: &quot;);</span><a href="#l427"></a>
<span id="l428">            sb.append(msg);</span><a href="#l428"></a>
<span id="l429">            if (thrown != null) {</span><a href="#l429"></a>
<span id="l430">                try {</span><a href="#l430"></a>
<span id="l431">                    StringWriter sw = new StringWriter();</span><a href="#l431"></a>
<span id="l432">                    PrintWriter pw = new PrintWriter(sw);</span><a href="#l432"></a>
<span id="l433">                    thrown.printStackTrace(pw);</span><a href="#l433"></a>
<span id="l434">                    pw.close();</span><a href="#l434"></a>
<span id="l435">                    sb.append(sw.toString());</span><a href="#l435"></a>
<span id="l436">                } catch (Exception ex) {</span><a href="#l436"></a>
<span id="l437">                    throw new AssertionError(ex);</span><a href="#l437"></a>
<span id="l438">                }</span><a href="#l438"></a>
<span id="l439">            }</span><a href="#l439"></a>
<span id="l440"></span><a href="#l440"></a>
<span id="l441">            return sb.toString();</span><a href="#l441"></a>
<span id="l442">        }</span><a href="#l442"></a>
<span id="l443"></span><a href="#l443"></a>
<span id="l444">        // Returns the caller's class and method's name; best effort</span><a href="#l444"></a>
<span id="l445">        // if cannot infer, return the logger's name.</span><a href="#l445"></a>
<span id="l446">        private String getCallerInfo() {</span><a href="#l446"></a>
<span id="l447">            String sourceClassName = null;</span><a href="#l447"></a>
<span id="l448">            String sourceMethodName = null;</span><a href="#l448"></a>
<span id="l449"></span><a href="#l449"></a>
<span id="l450">            JavaLangAccess access = SharedSecrets.getJavaLangAccess();</span><a href="#l450"></a>
<span id="l451">            Throwable throwable = new Throwable();</span><a href="#l451"></a>
<span id="l452">            int depth = access.getStackTraceDepth(throwable);</span><a href="#l452"></a>
<span id="l453"></span><a href="#l453"></a>
<span id="l454">            String logClassName = &quot;sun.util.logging.PlatformLogger&quot;;</span><a href="#l454"></a>
<span id="l455">            boolean lookingForLogger = true;</span><a href="#l455"></a>
<span id="l456">            for (int ix = 0; ix &lt; depth; ix++) {</span><a href="#l456"></a>
<span id="l457">                // Calling getStackTraceElement directly prevents the VM</span><a href="#l457"></a>
<span id="l458">                // from paying the cost of building the entire stack frame.</span><a href="#l458"></a>
<span id="l459">                StackTraceElement frame =</span><a href="#l459"></a>
<span id="l460">                    access.getStackTraceElement(throwable, ix);</span><a href="#l460"></a>
<span id="l461">                String cname = frame.getClassName();</span><a href="#l461"></a>
<span id="l462">                if (lookingForLogger) {</span><a href="#l462"></a>
<span id="l463">                    // Skip all frames until we have found the first logger frame.</span><a href="#l463"></a>
<span id="l464">                    if (cname.equals(logClassName)) {</span><a href="#l464"></a>
<span id="l465">                        lookingForLogger = false;</span><a href="#l465"></a>
<span id="l466">                    }</span><a href="#l466"></a>
<span id="l467">                } else {</span><a href="#l467"></a>
<span id="l468">                    if (!cname.equals(logClassName)) {</span><a href="#l468"></a>
<span id="l469">                        // We've found the relevant frame.</span><a href="#l469"></a>
<span id="l470">                        sourceClassName = cname;</span><a href="#l470"></a>
<span id="l471">                        sourceMethodName = frame.getMethodName();</span><a href="#l471"></a>
<span id="l472">                        break;</span><a href="#l472"></a>
<span id="l473">                    }</span><a href="#l473"></a>
<span id="l474">                }</span><a href="#l474"></a>
<span id="l475">            }</span><a href="#l475"></a>
<span id="l476"></span><a href="#l476"></a>
<span id="l477">            if (sourceClassName != null) {</span><a href="#l477"></a>
<span id="l478">                return sourceClassName + &quot; &quot; + sourceMethodName;</span><a href="#l478"></a>
<span id="l479">            } else {</span><a href="#l479"></a>
<span id="l480">                return name;</span><a href="#l480"></a>
<span id="l481">            }</span><a href="#l481"></a>
<span id="l482">        }</span><a href="#l482"></a>
<span id="l483">    }</span><a href="#l483"></a>
<span id="l484"></span><a href="#l484"></a>
<span id="l485">    /**</span><a href="#l485"></a>
<span id="l486">     * JavaLogger forwards all the calls to its corresponding</span><a href="#l486"></a>
<span id="l487">     * java.util.logging.Logger object.</span><a href="#l487"></a>
<span id="l488">     */</span><a href="#l488"></a>
<span id="l489">    static class JavaLogger extends LoggerProxy {</span><a href="#l489"></a>
<span id="l490">        private static final Map&lt;Integer, Object&gt; levelObjects =</span><a href="#l490"></a>
<span id="l491">            new HashMap&lt;&gt;();</span><a href="#l491"></a>
<span id="l492"></span><a href="#l492"></a>
<span id="l493">        static {</span><a href="#l493"></a>
<span id="l494">            if (LoggingSupport.isAvailable()) {</span><a href="#l494"></a>
<span id="l495">                // initialize the map to Level objects</span><a href="#l495"></a>
<span id="l496">                getLevelObjects();</span><a href="#l496"></a>
<span id="l497">            }</span><a href="#l497"></a>
<span id="l498">        }</span><a href="#l498"></a>
<span id="l499"></span><a href="#l499"></a>
<span id="l500">        private static void getLevelObjects() {</span><a href="#l500"></a>
<span id="l501">            // get all java.util.logging.Level objects</span><a href="#l501"></a>
<span id="l502">            int[] levelArray = new int[] {OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL};</span><a href="#l502"></a>
<span id="l503">            for (int l : levelArray) {</span><a href="#l503"></a>
<span id="l504">                Object level = LoggingSupport.parseLevel(getLevelName(l));</span><a href="#l504"></a>
<span id="l505">                levelObjects.put(l, level);</span><a href="#l505"></a>
<span id="l506">            }</span><a href="#l506"></a>
<span id="l507">        }</span><a href="#l507"></a>
<span id="l508"></span><a href="#l508"></a>
<span id="l509">        private final Object javaLogger;</span><a href="#l509"></a>
<span id="l510">        JavaLogger(String name) {</span><a href="#l510"></a>
<span id="l511">            this(name, 0);</span><a href="#l511"></a>
<span id="l512">        }</span><a href="#l512"></a>
<span id="l513"></span><a href="#l513"></a>
<span id="l514">        JavaLogger(String name, int level) {</span><a href="#l514"></a>
<span id="l515">            super(name, level);</span><a href="#l515"></a>
<span id="l516">            this.javaLogger = LoggingSupport.getLogger(name);</span><a href="#l516"></a>
<span id="l517">            if (level != 0) {</span><a href="#l517"></a>
<span id="l518">                // level has been updated and so set the Logger's level</span><a href="#l518"></a>
<span id="l519">                LoggingSupport.setLevel(javaLogger, levelObjects.get(level));</span><a href="#l519"></a>
<span id="l520">            }</span><a href="#l520"></a>
<span id="l521">        }</span><a href="#l521"></a>
<span id="l522"></span><a href="#l522"></a>
<span id="l523">       /**</span><a href="#l523"></a>
<span id="l524">        * Let Logger.log() do the filtering since if the level of a</span><a href="#l524"></a>
<span id="l525">        * platform logger is altered directly from</span><a href="#l525"></a>
<span id="l526">        * java.util.logging.Logger.setLevel(), the levelValue will</span><a href="#l526"></a>
<span id="l527">        * not be updated.</span><a href="#l527"></a>
<span id="l528">        */</span><a href="#l528"></a>
<span id="l529">        void doLog(int level, String msg) {</span><a href="#l529"></a>
<span id="l530">            LoggingSupport.log(javaLogger, levelObjects.get(level), msg);</span><a href="#l530"></a>
<span id="l531">        }</span><a href="#l531"></a>
<span id="l532"></span><a href="#l532"></a>
<span id="l533">        void doLog(int level, String msg, Throwable t) {</span><a href="#l533"></a>
<span id="l534">            LoggingSupport.log(javaLogger, levelObjects.get(level), msg, t);</span><a href="#l534"></a>
<span id="l535">        }</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">        void doLog(int level, String msg, Object... params) {</span><a href="#l537"></a>
<span id="l538">            // only pass String objects to the j.u.l.Logger which may</span><a href="#l538"></a>
<span id="l539">            // be created by untrusted code</span><a href="#l539"></a>
<span id="l540">            int len = (params != null) ? params.length : 0;</span><a href="#l540"></a>
<span id="l541">            Object[] sparams = new String[len];</span><a href="#l541"></a>
<span id="l542">            for (int i = 0; i &lt; len; i++) {</span><a href="#l542"></a>
<span id="l543">                sparams [i] = String.valueOf(params[i]);</span><a href="#l543"></a>
<span id="l544">            }</span><a href="#l544"></a>
<span id="l545">            LoggingSupport.log(javaLogger, levelObjects.get(level), msg, sparams);</span><a href="#l545"></a>
<span id="l546">        }</span><a href="#l546"></a>
<span id="l547"></span><a href="#l547"></a>
<span id="l548">        boolean isEnabled() {</span><a href="#l548"></a>
<span id="l549">            Object level = LoggingSupport.getLevel(javaLogger);</span><a href="#l549"></a>
<span id="l550">            return level == null || level.equals(levelObjects.get(OFF)) == false;</span><a href="#l550"></a>
<span id="l551">        }</span><a href="#l551"></a>
<span id="l552"></span><a href="#l552"></a>
<span id="l553">        int getLevel() {</span><a href="#l553"></a>
<span id="l554">            Object level = LoggingSupport.getLevel(javaLogger);</span><a href="#l554"></a>
<span id="l555">            if (level != null) {</span><a href="#l555"></a>
<span id="l556">                for (Map.Entry&lt;Integer, Object&gt; l : levelObjects.entrySet()) {</span><a href="#l556"></a>
<span id="l557">                    if (level == l.getValue()) {</span><a href="#l557"></a>
<span id="l558">                        return l.getKey();</span><a href="#l558"></a>
<span id="l559">                    }</span><a href="#l559"></a>
<span id="l560">                }</span><a href="#l560"></a>
<span id="l561">            }</span><a href="#l561"></a>
<span id="l562">            return 0;</span><a href="#l562"></a>
<span id="l563">        }</span><a href="#l563"></a>
<span id="l564"></span><a href="#l564"></a>
<span id="l565">        void setLevel(int newLevel) {</span><a href="#l565"></a>
<span id="l566">            levelValue = newLevel;</span><a href="#l566"></a>
<span id="l567">            LoggingSupport.setLevel(javaLogger, levelObjects.get(newLevel));</span><a href="#l567"></a>
<span id="l568">        }</span><a href="#l568"></a>
<span id="l569"></span><a href="#l569"></a>
<span id="l570">        public boolean isLoggable(int level) {</span><a href="#l570"></a>
<span id="l571">            return LoggingSupport.isLoggable(javaLogger, levelObjects.get(level));</span><a href="#l571"></a>
<span id="l572">        }</span><a href="#l572"></a>
<span id="l573">    }</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">    private static String getLevelName(int level) {</span><a href="#l575"></a>
<span id="l576">        switch (level) {</span><a href="#l576"></a>
<span id="l577">            case OFF     : return &quot;OFF&quot;;</span><a href="#l577"></a>
<span id="l578">            case SEVERE  : return &quot;SEVERE&quot;;</span><a href="#l578"></a>
<span id="l579">            case WARNING : return &quot;WARNING&quot;;</span><a href="#l579"></a>
<span id="l580">            case INFO    : return &quot;INFO&quot;;</span><a href="#l580"></a>
<span id="l581">            case CONFIG  : return &quot;CONFIG&quot;;</span><a href="#l581"></a>
<span id="l582">            case FINE    : return &quot;FINE&quot;;</span><a href="#l582"></a>
<span id="l583">            case FINER   : return &quot;FINER&quot;;</span><a href="#l583"></a>
<span id="l584">            case FINEST  : return &quot;FINEST&quot;;</span><a href="#l584"></a>
<span id="l585">            case ALL     : return &quot;ALL&quot;;</span><a href="#l585"></a>
<span id="l586">            default      : return &quot;UNKNOWN&quot;;</span><a href="#l586"></a>
<span id="l587">        }</span><a href="#l587"></a>
<span id="l588">    }</span><a href="#l588"></a>
<span id="l589"></span><a href="#l589"></a>
<span id="l590">}</span><a href="#l590"></a></pre>
<div class="sourcelast"></div>
</div>
</div>
</div>



<div class="container"><div class="main footer">
&copy 2007, <script>document.write(new Date().getFullYear())</script> Oracle and/or its affiliates<br/>
<a href="http://openjdk.java.net/legal/tou/">Terms of Use</a>
&#183; <a href="http://www.oracle.com/us/legal/privacy/">Privacy</a>
&#183; <a href="https://openjdk.java.net/legal/openjdk-trademark-notice.html">Trademarks</a>
</div></div>

</body>
</html>

