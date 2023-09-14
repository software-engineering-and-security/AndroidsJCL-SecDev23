<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 446338ed795d src/share/classes/sun/security/ssl/SSLLogger.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/446338ed795d">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/446338ed795d">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/446338ed795d">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/446338ed795d/src/share/classes/sun/security/ssl/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/security/ssl/SSLLogger.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/446338ed795d/src/share/classes/sun/security/ssl/SSLLogger.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/446338ed795d/src/share/classes/sun/security/ssl/SSLLogger.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/446338ed795d/src/share/classes/sun/security/ssl/SSLLogger.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/446338ed795d/src/share/classes/sun/security/ssl/SSLLogger.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/446338ed795d/src/share/classes/sun/security/ssl/SSLLogger.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/security/ssl/SSLLogger.java @ 14569:446338ed795d</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8267729: Improve TLS client handshaking
Reviewed-by: andrew</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#109;&#98;&#97;&#108;&#97;&#111;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 16 Sep 2021 14:49:37 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/3ad72295b457/src/share/classes/sun/security/ssl/SSLLogger.java">3ad72295b457</a> </td>
</tr>
<tr>
 <th class="author">children</th>
 <td class="author"></td>
</tr>
</table>

<div class="overflow">
<div class="sourcefirst linewraptoggle">line wrap: <a class="linewraplink" href="javascript:toggleLinewrap()">on</a></div>
<div class="sourcefirst"> line source</div>
<pre class="sourcelines stripes4 wrap">
<span id="l1">/*</span><a href="#l1"></a>
<span id="l2"> * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.security.ssl;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.ByteArrayInputStream;</span><a href="#l28"></a>
<span id="l29">import java.io.ByteArrayOutputStream;</span><a href="#l29"></a>
<span id="l30">import java.io.IOException;</span><a href="#l30"></a>
<span id="l31">import java.io.PrintStream;</span><a href="#l31"></a>
<span id="l32">import java.nio.ByteBuffer;</span><a href="#l32"></a>
<span id="l33">import java.security.cert.Certificate;</span><a href="#l33"></a>
<span id="l34">import java.security.cert.Extension;</span><a href="#l34"></a>
<span id="l35">import java.security.cert.X509Certificate;</span><a href="#l35"></a>
<span id="l36">import java.text.MessageFormat;</span><a href="#l36"></a>
<span id="l37">import java.text.SimpleDateFormat;</span><a href="#l37"></a>
<span id="l38">import java.util.Date;</span><a href="#l38"></a>
<span id="l39">import java.util.Locale;</span><a href="#l39"></a>
<span id="l40">import java.util.logging.LogRecord;</span><a href="#l40"></a>
<span id="l41">import java.util.logging.Logger;</span><a href="#l41"></a>
<span id="l42">import java.util.logging.Level;</span><a href="#l42"></a>
<span id="l43">import java.util.Map;</span><a href="#l43"></a>
<span id="l44">import java.util.ResourceBundle;</span><a href="#l44"></a>
<span id="l45">import sun.security.action.GetPropertyAction;</span><a href="#l45"></a>
<span id="l46">import sun.misc.HexDumpEncoder;</span><a href="#l46"></a>
<span id="l47">import sun.security.x509.*;</span><a href="#l47"></a>
<span id="l48"></span><a href="#l48"></a>
<span id="l49">/**</span><a href="#l49"></a>
<span id="l50"> * Implementation of SSL logger.</span><a href="#l50"></a>
<span id="l51"> *</span><a href="#l51"></a>
<span id="l52"> * If the system property &quot;javax.net.debug&quot; is not defined, the debug logging</span><a href="#l52"></a>
<span id="l53"> * is turned off.  If the system property &quot;javax.net.debug&quot; is defined as</span><a href="#l53"></a>
<span id="l54"> * empty, the debug logger is specified by System.getLogger(&quot;javax.net.ssl&quot;),</span><a href="#l54"></a>
<span id="l55"> * and applications can customize and configure the logger or use external</span><a href="#l55"></a>
<span id="l56"> * logging mechanisms.  If the system property &quot;javax.net.debug&quot; is defined</span><a href="#l56"></a>
<span id="l57"> * and non-empty, a private debug logger implemented in this class is used.</span><a href="#l57"></a>
<span id="l58"> */</span><a href="#l58"></a>
<span id="l59">public final class SSLLogger {</span><a href="#l59"></a>
<span id="l60">    private static final Logger logger;</span><a href="#l60"></a>
<span id="l61">    private static final String property;</span><a href="#l61"></a>
<span id="l62">    public static final boolean isOn;</span><a href="#l62"></a>
<span id="l63"></span><a href="#l63"></a>
<span id="l64">    static {</span><a href="#l64"></a>
<span id="l65">        String p = GetPropertyAction.privilegedGetProperty(&quot;javax.net.debug&quot;);</span><a href="#l65"></a>
<span id="l66">        if (p != null) {</span><a href="#l66"></a>
<span id="l67">            if (p.isEmpty()) {</span><a href="#l67"></a>
<span id="l68">                property = &quot;&quot;;</span><a href="#l68"></a>
<span id="l69">                logger = Logger.getLogger(&quot;javax.net.ssl&quot;);</span><a href="#l69"></a>
<span id="l70">            } else {</span><a href="#l70"></a>
<span id="l71">                property = p.toLowerCase(Locale.ENGLISH);</span><a href="#l71"></a>
<span id="l72">                if (property.equals(&quot;help&quot;)) {</span><a href="#l72"></a>
<span id="l73">                    help();</span><a href="#l73"></a>
<span id="l74">                }</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76">                logger = new SSLConsoleLogger(&quot;javax.net.ssl&quot;, p);</span><a href="#l76"></a>
<span id="l77">            }</span><a href="#l77"></a>
<span id="l78">            isOn = true;</span><a href="#l78"></a>
<span id="l79">        } else {</span><a href="#l79"></a>
<span id="l80">            property = null;</span><a href="#l80"></a>
<span id="l81">            logger = null;</span><a href="#l81"></a>
<span id="l82">            isOn = false;</span><a href="#l82"></a>
<span id="l83">        }</span><a href="#l83"></a>
<span id="l84">    }</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    private static void help() {</span><a href="#l86"></a>
<span id="l87">        System.err.println();</span><a href="#l87"></a>
<span id="l88">        System.err.println(&quot;help           print the help messages&quot;);</span><a href="#l88"></a>
<span id="l89">        System.err.println(&quot;expand         expand debugging information&quot;);</span><a href="#l89"></a>
<span id="l90">        System.err.println();</span><a href="#l90"></a>
<span id="l91">        System.err.println(&quot;all            turn on all debugging&quot;);</span><a href="#l91"></a>
<span id="l92">        System.err.println(&quot;ssl            turn on ssl debugging&quot;);</span><a href="#l92"></a>
<span id="l93">        System.err.println();</span><a href="#l93"></a>
<span id="l94">        System.err.println(&quot;The following can be used with ssl:&quot;);</span><a href="#l94"></a>
<span id="l95">        System.err.println(&quot;\trecord       enable per-record tracing&quot;);</span><a href="#l95"></a>
<span id="l96">        System.err.println(&quot;\thandshake    print each handshake message&quot;);</span><a href="#l96"></a>
<span id="l97">        System.err.println(&quot;\tkeygen       print key generation data&quot;);</span><a href="#l97"></a>
<span id="l98">        System.err.println(&quot;\tsession      print session activity&quot;);</span><a href="#l98"></a>
<span id="l99">        System.err.println(&quot;\tdefaultctx   print default SSL initialization&quot;);</span><a href="#l99"></a>
<span id="l100">        System.err.println(&quot;\tsslctx       print SSLContext tracing&quot;);</span><a href="#l100"></a>
<span id="l101">        System.err.println(&quot;\tsessioncache print session cache tracing&quot;);</span><a href="#l101"></a>
<span id="l102">        System.err.println(&quot;\tkeymanager   print key manager tracing&quot;);</span><a href="#l102"></a>
<span id="l103">        System.err.println(&quot;\ttrustmanager print trust manager tracing&quot;);</span><a href="#l103"></a>
<span id="l104">        System.err.println(&quot;\tpluggability print pluggability tracing&quot;);</span><a href="#l104"></a>
<span id="l105">        System.err.println();</span><a href="#l105"></a>
<span id="l106">        System.err.println(&quot;\thandshake debugging can be widened with:&quot;);</span><a href="#l106"></a>
<span id="l107">        System.err.println(&quot;\tdata         hex dump of each handshake message&quot;);</span><a href="#l107"></a>
<span id="l108">        System.err.println(&quot;\tverbose      verbose handshake message printing&quot;);</span><a href="#l108"></a>
<span id="l109">        System.err.println();</span><a href="#l109"></a>
<span id="l110">        System.err.println(&quot;\trecord debugging can be widened with:&quot;);</span><a href="#l110"></a>
<span id="l111">        System.err.println(&quot;\tplaintext    hex dump of record plaintext&quot;);</span><a href="#l111"></a>
<span id="l112">        System.err.println(&quot;\tpacket       print raw SSL/TLS packets&quot;);</span><a href="#l112"></a>
<span id="l113">        System.err.println();</span><a href="#l113"></a>
<span id="l114">        System.exit(0);</span><a href="#l114"></a>
<span id="l115">    }</span><a href="#l115"></a>
<span id="l116"></span><a href="#l116"></a>
<span id="l117">    /**</span><a href="#l117"></a>
<span id="l118">     * Return true if the &quot;javax.net.debug&quot; property contains the</span><a href="#l118"></a>
<span id="l119">     * debug check points, or System.Logger is used.</span><a href="#l119"></a>
<span id="l120">     */</span><a href="#l120"></a>
<span id="l121">    public static boolean isOn(String checkPoints) {</span><a href="#l121"></a>
<span id="l122">        if (property == null) {              // debugging is turned off</span><a href="#l122"></a>
<span id="l123">            return false;</span><a href="#l123"></a>
<span id="l124">        } else if (property.isEmpty()) {     // use System.Logger</span><a href="#l124"></a>
<span id="l125">            return true;</span><a href="#l125"></a>
<span id="l126">        }                                   // use provider logger</span><a href="#l126"></a>
<span id="l127"></span><a href="#l127"></a>
<span id="l128">        String[] options = checkPoints.split(&quot;,&quot;);</span><a href="#l128"></a>
<span id="l129">        for (String option : options) {</span><a href="#l129"></a>
<span id="l130">            option = option.trim();</span><a href="#l130"></a>
<span id="l131">            if (!SSLLogger.hasOption(option)) {</span><a href="#l131"></a>
<span id="l132">                return false;</span><a href="#l132"></a>
<span id="l133">            }</span><a href="#l133"></a>
<span id="l134">        }</span><a href="#l134"></a>
<span id="l135"></span><a href="#l135"></a>
<span id="l136">        return true;</span><a href="#l136"></a>
<span id="l137">    }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">    private static boolean hasOption(String option) {</span><a href="#l139"></a>
<span id="l140">        option = option.toLowerCase(Locale.ENGLISH);</span><a href="#l140"></a>
<span id="l141">        if (property.contains(&quot;all&quot;)) {</span><a href="#l141"></a>
<span id="l142">            return true;</span><a href="#l142"></a>
<span id="l143">        } else {</span><a href="#l143"></a>
<span id="l144">            int offset = property.indexOf(&quot;ssl&quot;);</span><a href="#l144"></a>
<span id="l145">            if (offset != -1 &amp;&amp; property.indexOf(&quot;sslctx&quot;, offset) != -1) {</span><a href="#l145"></a>
<span id="l146">                // don't enable data and plaintext options by default</span><a href="#l146"></a>
<span id="l147">                if (!(option.equals(&quot;data&quot;)</span><a href="#l147"></a>
<span id="l148">                        || option.equals(&quot;packet&quot;)</span><a href="#l148"></a>
<span id="l149">                        || option.equals(&quot;plaintext&quot;))) {</span><a href="#l149"></a>
<span id="l150">                    return true;</span><a href="#l150"></a>
<span id="l151">                }</span><a href="#l151"></a>
<span id="l152">            }</span><a href="#l152"></a>
<span id="l153">        }</span><a href="#l153"></a>
<span id="l154"></span><a href="#l154"></a>
<span id="l155">        return property.contains(option);</span><a href="#l155"></a>
<span id="l156">    }</span><a href="#l156"></a>
<span id="l157"></span><a href="#l157"></a>
<span id="l158">    public static void severe(String msg, Object... params) {</span><a href="#l158"></a>
<span id="l159">        SSLLogger.log(Level.SEVERE, msg, params);</span><a href="#l159"></a>
<span id="l160">    }</span><a href="#l160"></a>
<span id="l161"></span><a href="#l161"></a>
<span id="l162">    public static void warning(String msg, Object... params) {</span><a href="#l162"></a>
<span id="l163">        SSLLogger.log(Level.WARNING, msg, params);</span><a href="#l163"></a>
<span id="l164">    }</span><a href="#l164"></a>
<span id="l165"></span><a href="#l165"></a>
<span id="l166">    public static void info(String msg, Object... params) {</span><a href="#l166"></a>
<span id="l167">        SSLLogger.log(Level.INFO, msg, params);</span><a href="#l167"></a>
<span id="l168">    }</span><a href="#l168"></a>
<span id="l169"></span><a href="#l169"></a>
<span id="l170">    public static void fine(String msg, Object... params) {</span><a href="#l170"></a>
<span id="l171">        SSLLogger.log(Level.FINE, msg, params);</span><a href="#l171"></a>
<span id="l172">    }</span><a href="#l172"></a>
<span id="l173"></span><a href="#l173"></a>
<span id="l174">    public static void finer(String msg, Object... params) {</span><a href="#l174"></a>
<span id="l175">        SSLLogger.log(Level.FINER, msg, params);</span><a href="#l175"></a>
<span id="l176">    }</span><a href="#l176"></a>
<span id="l177"></span><a href="#l177"></a>
<span id="l178">    public static void finest(String msg, Object... params) {</span><a href="#l178"></a>
<span id="l179">        SSLLogger.log(Level.ALL, msg, params);</span><a href="#l179"></a>
<span id="l180">    }</span><a href="#l180"></a>
<span id="l181"></span><a href="#l181"></a>
<span id="l182">    private static void log(Level level, String msg, Object... params) {</span><a href="#l182"></a>
<span id="l183">        if (logger != null &amp;&amp; logger.isLoggable(level)) {</span><a href="#l183"></a>
<span id="l184">            if (params == null || params.length == 0) {</span><a href="#l184"></a>
<span id="l185">                logger.log(level, msg);</span><a href="#l185"></a>
<span id="l186">            } else {</span><a href="#l186"></a>
<span id="l187">                try {</span><a href="#l187"></a>
<span id="l188">                    String formatted =</span><a href="#l188"></a>
<span id="l189">                            SSLSimpleFormatter.formatParameters(params);</span><a href="#l189"></a>
<span id="l190">                    logger.log(level, msg, formatted);</span><a href="#l190"></a>
<span id="l191">                } catch (Exception exp) {</span><a href="#l191"></a>
<span id="l192">                    // ignore it, just for debugging.</span><a href="#l192"></a>
<span id="l193">                }</span><a href="#l193"></a>
<span id="l194">            }</span><a href="#l194"></a>
<span id="l195">        }</span><a href="#l195"></a>
<span id="l196">    }</span><a href="#l196"></a>
<span id="l197"></span><a href="#l197"></a>
<span id="l198">    static String toString(Object... params) {</span><a href="#l198"></a>
<span id="l199">        try {</span><a href="#l199"></a>
<span id="l200">            return SSLSimpleFormatter.formatParameters(params);</span><a href="#l200"></a>
<span id="l201">        } catch (Exception exp) {</span><a href="#l201"></a>
<span id="l202">            return &quot;unexpected exception thrown: &quot; + exp.getMessage();</span><a href="#l202"></a>
<span id="l203">        }</span><a href="#l203"></a>
<span id="l204">    }</span><a href="#l204"></a>
<span id="l205"></span><a href="#l205"></a>
<span id="l206">    private static class SSLConsoleLogger extends Logger {</span><a href="#l206"></a>
<span id="l207">        private final String loggerName;</span><a href="#l207"></a>
<span id="l208">        private final boolean useCompactFormat;</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">        SSLConsoleLogger(String loggerName, String options) {</span><a href="#l210"></a>
<span id="l211">            super(loggerName, null);</span><a href="#l211"></a>
<span id="l212">            this.loggerName = loggerName;</span><a href="#l212"></a>
<span id="l213">            options = options.toLowerCase(Locale.ENGLISH);</span><a href="#l213"></a>
<span id="l214">            this.useCompactFormat = !options.contains(&quot;expand&quot;);</span><a href="#l214"></a>
<span id="l215">        }</span><a href="#l215"></a>
<span id="l216"></span><a href="#l216"></a>
<span id="l217">        public String getName() {</span><a href="#l217"></a>
<span id="l218">            return loggerName;</span><a href="#l218"></a>
<span id="l219">        }</span><a href="#l219"></a>
<span id="l220"></span><a href="#l220"></a>
<span id="l221">        public boolean isLoggable(Level level) {</span><a href="#l221"></a>
<span id="l222">            return (level != Level.OFF);</span><a href="#l222"></a>
<span id="l223">        }</span><a href="#l223"></a>
<span id="l224"></span><a href="#l224"></a>
<span id="l225">        @Override</span><a href="#l225"></a>
<span id="l226">        public void log(LogRecord record) {</span><a href="#l226"></a>
<span id="l227">            if (isLoggable(record.getLevel())) {</span><a href="#l227"></a>
<span id="l228">                try {</span><a href="#l228"></a>
<span id="l229">                    String formatted = null;</span><a href="#l229"></a>
<span id="l230">                    if (record.getThrown() != null) {</span><a href="#l230"></a>
<span id="l231">                        formatted =</span><a href="#l231"></a>
<span id="l232">                                SSLSimpleFormatter.format(this, record.getLevel(),</span><a href="#l232"></a>
<span id="l233">                                        record.getMessage(),</span><a href="#l233"></a>
<span id="l234">                                        record.getThrown());</span><a href="#l234"></a>
<span id="l235">                    } else {</span><a href="#l235"></a>
<span id="l236">                        formatted =</span><a href="#l236"></a>
<span id="l237">                                SSLSimpleFormatter.format(this, record.getLevel(),</span><a href="#l237"></a>
<span id="l238">                                        record.getMessage(),</span><a href="#l238"></a>
<span id="l239">                                        record.getParameters());</span><a href="#l239"></a>
<span id="l240">                    }</span><a href="#l240"></a>
<span id="l241">                    System.err.write(formatted.getBytes(&quot;UTF-8&quot;));</span><a href="#l241"></a>
<span id="l242">                } catch (Exception exp) {</span><a href="#l242"></a>
<span id="l243">                    // ignore it, just for debugging.</span><a href="#l243"></a>
<span id="l244">                }</span><a href="#l244"></a>
<span id="l245">            }</span><a href="#l245"></a>
<span id="l246">        };</span><a href="#l246"></a>
<span id="l247">    }</span><a href="#l247"></a>
<span id="l248"></span><a href="#l248"></a>
<span id="l249">    private static class SSLSimpleFormatter {</span><a href="#l249"></a>
<span id="l250">        private static final ThreadLocal&lt;SimpleDateFormat&gt; dateFormat =</span><a href="#l250"></a>
<span id="l251">            new ThreadLocal&lt;SimpleDateFormat&gt;() {</span><a href="#l251"></a>
<span id="l252">                @Override protected SimpleDateFormat initialValue() {</span><a href="#l252"></a>
<span id="l253">                    return new SimpleDateFormat(</span><a href="#l253"></a>
<span id="l254">                            &quot;yyyy-MM-dd kk:mm:ss.SSS z&quot;, Locale.ENGLISH);</span><a href="#l254"></a>
<span id="l255">                }</span><a href="#l255"></a>
<span id="l256">            };</span><a href="#l256"></a>
<span id="l257"></span><a href="#l257"></a>
<span id="l258">        private static final MessageFormat basicCertFormat = new MessageFormat(</span><a href="#l258"></a>
<span id="l259">                &quot;\&quot;version\&quot;            : \&quot;v{0}\&quot;,\n&quot; +</span><a href="#l259"></a>
<span id="l260">                &quot;\&quot;serial number\&quot;      : \&quot;{1}\&quot;,\n&quot; +</span><a href="#l260"></a>
<span id="l261">                &quot;\&quot;signature algorithm\&quot;: \&quot;{2}\&quot;,\n&quot; +</span><a href="#l261"></a>
<span id="l262">                &quot;\&quot;issuer\&quot;             : \&quot;{3}\&quot;,\n&quot; +</span><a href="#l262"></a>
<span id="l263">                &quot;\&quot;not before\&quot;         : \&quot;{4}\&quot;,\n&quot; +</span><a href="#l263"></a>
<span id="l264">                &quot;\&quot;not  after\&quot;         : \&quot;{5}\&quot;,\n&quot; +</span><a href="#l264"></a>
<span id="l265">                &quot;\&quot;subject\&quot;            : \&quot;{6}\&quot;,\n&quot; +</span><a href="#l265"></a>
<span id="l266">                &quot;\&quot;subject public key\&quot; : \&quot;{7}\&quot;\n&quot;,</span><a href="#l266"></a>
<span id="l267">                Locale.ENGLISH);</span><a href="#l267"></a>
<span id="l268"></span><a href="#l268"></a>
<span id="l269">        private static final MessageFormat extendedCertFormart =</span><a href="#l269"></a>
<span id="l270">            new MessageFormat(</span><a href="#l270"></a>
<span id="l271">                &quot;\&quot;version\&quot;            : \&quot;v{0}\&quot;,\n&quot; +</span><a href="#l271"></a>
<span id="l272">                &quot;\&quot;serial number\&quot;      : \&quot;{1}\&quot;,\n&quot; +</span><a href="#l272"></a>
<span id="l273">                &quot;\&quot;signature algorithm\&quot;: \&quot;{2}\&quot;,\n&quot; +</span><a href="#l273"></a>
<span id="l274">                &quot;\&quot;issuer\&quot;             : \&quot;{3}\&quot;,\n&quot; +</span><a href="#l274"></a>
<span id="l275">                &quot;\&quot;not before\&quot;         : \&quot;{4}\&quot;,\n&quot; +</span><a href="#l275"></a>
<span id="l276">                &quot;\&quot;not  after\&quot;         : \&quot;{5}\&quot;,\n&quot; +</span><a href="#l276"></a>
<span id="l277">                &quot;\&quot;subject\&quot;            : \&quot;{6}\&quot;,\n&quot; +</span><a href="#l277"></a>
<span id="l278">                &quot;\&quot;subject public key\&quot; : \&quot;{7}\&quot;,\n&quot; +</span><a href="#l278"></a>
<span id="l279">                &quot;\&quot;extensions\&quot;         : [\n&quot; +</span><a href="#l279"></a>
<span id="l280">                &quot;{8}\n&quot; +</span><a href="#l280"></a>
<span id="l281">                &quot;]\n&quot;,</span><a href="#l281"></a>
<span id="l282">                Locale.ENGLISH);</span><a href="#l282"></a>
<span id="l283"></span><a href="#l283"></a>
<span id="l284">        //</span><a href="#l284"></a>
<span id="l285">        // private static MessageFormat certExtFormat = new MessageFormat(</span><a href="#l285"></a>
<span id="l286">        //         &quot;{0} [{1}] '{'\n&quot; +</span><a href="#l286"></a>
<span id="l287">        //         &quot;  critical: {2}\n&quot; +</span><a href="#l287"></a>
<span id="l288">        //         &quot;  value: {3}\n&quot; +</span><a href="#l288"></a>
<span id="l289">        //         &quot;'}'&quot;,</span><a href="#l289"></a>
<span id="l290">        //         Locale.ENGLISH);</span><a href="#l290"></a>
<span id="l291">        //</span><a href="#l291"></a>
<span id="l292"></span><a href="#l292"></a>
<span id="l293">        private static final MessageFormat messageFormatNoParas =</span><a href="#l293"></a>
<span id="l294">            new MessageFormat(</span><a href="#l294"></a>
<span id="l295">                &quot;'{'\n&quot; +</span><a href="#l295"></a>
<span id="l296">                &quot;  \&quot;logger\&quot;      : \&quot;{0}\&quot;,\n&quot; +</span><a href="#l296"></a>
<span id="l297">                &quot;  \&quot;level\&quot;       : \&quot;{1}\&quot;,\n&quot; +</span><a href="#l297"></a>
<span id="l298">                &quot;  \&quot;thread id\&quot;   : \&quot;{2}\&quot;,\n&quot; +</span><a href="#l298"></a>
<span id="l299">                &quot;  \&quot;thread name\&quot; : \&quot;{3}\&quot;,\n&quot; +</span><a href="#l299"></a>
<span id="l300">                &quot;  \&quot;time\&quot;        : \&quot;{4}\&quot;,\n&quot; +</span><a href="#l300"></a>
<span id="l301">                &quot;  \&quot;caller\&quot;      : \&quot;{5}\&quot;,\n&quot; +</span><a href="#l301"></a>
<span id="l302">                &quot;  \&quot;message\&quot;     : \&quot;{6}\&quot;\n&quot; +</span><a href="#l302"></a>
<span id="l303">                &quot;'}'\n&quot;,</span><a href="#l303"></a>
<span id="l304">                Locale.ENGLISH);</span><a href="#l304"></a>
<span id="l305"></span><a href="#l305"></a>
<span id="l306">        private static final MessageFormat messageCompactFormatNoParas =</span><a href="#l306"></a>
<span id="l307">            new MessageFormat(</span><a href="#l307"></a>
<span id="l308">                &quot;{0}|{1}|{2}|{3}|{4}|{5}|{6}\n&quot;,</span><a href="#l308"></a>
<span id="l309">                Locale.ENGLISH);</span><a href="#l309"></a>
<span id="l310"></span><a href="#l310"></a>
<span id="l311">        private static final MessageFormat messageFormatWithParas =</span><a href="#l311"></a>
<span id="l312">            new MessageFormat(</span><a href="#l312"></a>
<span id="l313">                &quot;'{'\n&quot; +</span><a href="#l313"></a>
<span id="l314">                &quot;  \&quot;logger\&quot;      : \&quot;{0}\&quot;,\n&quot; +</span><a href="#l314"></a>
<span id="l315">                &quot;  \&quot;level\&quot;       : \&quot;{1}\&quot;,\n&quot; +</span><a href="#l315"></a>
<span id="l316">                &quot;  \&quot;thread id\&quot;   : \&quot;{2}\&quot;,\n&quot; +</span><a href="#l316"></a>
<span id="l317">                &quot;  \&quot;thread name\&quot; : \&quot;{3}\&quot;,\n&quot; +</span><a href="#l317"></a>
<span id="l318">                &quot;  \&quot;time\&quot;        : \&quot;{4}\&quot;,\n&quot; +</span><a href="#l318"></a>
<span id="l319">                &quot;  \&quot;caller\&quot;      : \&quot;{5}\&quot;,\n&quot; +</span><a href="#l319"></a>
<span id="l320">                &quot;  \&quot;message\&quot;     : \&quot;{6}\&quot;,\n&quot; +</span><a href="#l320"></a>
<span id="l321">                &quot;  \&quot;specifics\&quot;   : [\n&quot; +</span><a href="#l321"></a>
<span id="l322">                &quot;{7}\n&quot; +</span><a href="#l322"></a>
<span id="l323">                &quot;  ]\n&quot; +</span><a href="#l323"></a>
<span id="l324">                &quot;'}'\n&quot;,</span><a href="#l324"></a>
<span id="l325">                Locale.ENGLISH);</span><a href="#l325"></a>
<span id="l326"></span><a href="#l326"></a>
<span id="l327">        private static final MessageFormat messageCompactFormatWithParas =</span><a href="#l327"></a>
<span id="l328">            new MessageFormat(</span><a href="#l328"></a>
<span id="l329">                &quot;{0}|{1}|{2}|{3}|{4}|{5}|{6} (\n&quot; +</span><a href="#l329"></a>
<span id="l330">                &quot;{7}\n&quot; +</span><a href="#l330"></a>
<span id="l331">                &quot;)\n&quot;,</span><a href="#l331"></a>
<span id="l332">                Locale.ENGLISH);</span><a href="#l332"></a>
<span id="l333"></span><a href="#l333"></a>
<span id="l334">        private static final MessageFormat keyObjectFormat = new MessageFormat(</span><a href="#l334"></a>
<span id="l335">                &quot;\&quot;{0}\&quot; : '{'\n&quot; +</span><a href="#l335"></a>
<span id="l336">                &quot;{1}&quot; +</span><a href="#l336"></a>
<span id="l337">                &quot;'}'\n&quot;,</span><a href="#l337"></a>
<span id="l338">                Locale.ENGLISH);</span><a href="#l338"></a>
<span id="l339"></span><a href="#l339"></a>
<span id="l340">        // INFO: [TH: 123450] 2011-08-20 23:12:32.3225 PDT</span><a href="#l340"></a>
<span id="l341">        //     log message</span><a href="#l341"></a>
<span id="l342">        //     log message</span><a href="#l342"></a>
<span id="l343">        //     ...</span><a href="#l343"></a>
<span id="l344">        private static String format(SSLConsoleLogger logger, Level level,</span><a href="#l344"></a>
<span id="l345">                    String message, Object ... parameters) {</span><a href="#l345"></a>
<span id="l346"></span><a href="#l346"></a>
<span id="l347">            if (parameters == null || parameters.length == 0) {</span><a href="#l347"></a>
<span id="l348">                Object[] messageFields = {</span><a href="#l348"></a>
<span id="l349">                    logger.loggerName,</span><a href="#l349"></a>
<span id="l350">                    level.getName(),</span><a href="#l350"></a>
<span id="l351">                    Utilities.toHexString(Thread.currentThread().getId()),</span><a href="#l351"></a>
<span id="l352">                    Thread.currentThread().getName(),</span><a href="#l352"></a>
<span id="l353">                    dateFormat.get().format(new Date(System.currentTimeMillis())),</span><a href="#l353"></a>
<span id="l354">                    formatCaller(),</span><a href="#l354"></a>
<span id="l355">                    message</span><a href="#l355"></a>
<span id="l356">                };</span><a href="#l356"></a>
<span id="l357"></span><a href="#l357"></a>
<span id="l358">                if (logger.useCompactFormat) {</span><a href="#l358"></a>
<span id="l359">                    return messageCompactFormatNoParas.format(messageFields);</span><a href="#l359"></a>
<span id="l360">                } else {</span><a href="#l360"></a>
<span id="l361">                    return messageFormatNoParas.format(messageFields);</span><a href="#l361"></a>
<span id="l362">                }</span><a href="#l362"></a>
<span id="l363">            }</span><a href="#l363"></a>
<span id="l364"></span><a href="#l364"></a>
<span id="l365">            Object[] messageFields = {</span><a href="#l365"></a>
<span id="l366">                    logger.loggerName,</span><a href="#l366"></a>
<span id="l367">                    level.getName(),</span><a href="#l367"></a>
<span id="l368">                    Utilities.toHexString(Thread.currentThread().getId()),</span><a href="#l368"></a>
<span id="l369">                    Thread.currentThread().getName(),</span><a href="#l369"></a>
<span id="l370">                    dateFormat.get().format(new Date(System.currentTimeMillis())),</span><a href="#l370"></a>
<span id="l371">                    formatCaller(),</span><a href="#l371"></a>
<span id="l372">                    message,</span><a href="#l372"></a>
<span id="l373">                    (logger.useCompactFormat ?</span><a href="#l373"></a>
<span id="l374">                        formatParameters(parameters) :</span><a href="#l374"></a>
<span id="l375">                        Utilities.indent(formatParameters(parameters)))</span><a href="#l375"></a>
<span id="l376">                };</span><a href="#l376"></a>
<span id="l377"></span><a href="#l377"></a>
<span id="l378">            if (logger.useCompactFormat) {</span><a href="#l378"></a>
<span id="l379">                return messageCompactFormatWithParas.format(messageFields);</span><a href="#l379"></a>
<span id="l380">            } else {</span><a href="#l380"></a>
<span id="l381">                return messageFormatWithParas.format(messageFields);</span><a href="#l381"></a>
<span id="l382">            }</span><a href="#l382"></a>
<span id="l383">        }</span><a href="#l383"></a>
<span id="l384"></span><a href="#l384"></a>
<span id="l385">        private static String formatCaller() {</span><a href="#l385"></a>
<span id="l386">            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();</span><a href="#l386"></a>
<span id="l387">            for (int i=1; i&lt;stElements.length; i++) {</span><a href="#l387"></a>
<span id="l388">                StackTraceElement ste = stElements[i];</span><a href="#l388"></a>
<span id="l389">                if (!ste.getClassName().startsWith(SSLLogger.class.getName()) &amp;&amp;</span><a href="#l389"></a>
<span id="l390">                    !ste.getClassName().startsWith(Logger.class.getName())) {</span><a href="#l390"></a>
<span id="l391">                   return ste.getFileName() + &quot;:&quot; + ste.getLineNumber();</span><a href="#l391"></a>
<span id="l392">                }</span><a href="#l392"></a>
<span id="l393">            }</span><a href="#l393"></a>
<span id="l394">            return &quot;unknown caller&quot;;</span><a href="#l394"></a>
<span id="l395">        }</span><a href="#l395"></a>
<span id="l396"></span><a href="#l396"></a>
<span id="l397">        private static String formatParameters(Object ... parameters) {</span><a href="#l397"></a>
<span id="l398">            StringBuilder builder = new StringBuilder(512);</span><a href="#l398"></a>
<span id="l399">            boolean isFirst = true;</span><a href="#l399"></a>
<span id="l400">            for (Object parameter : parameters) {</span><a href="#l400"></a>
<span id="l401">                if (isFirst) {</span><a href="#l401"></a>
<span id="l402">                    isFirst = false;</span><a href="#l402"></a>
<span id="l403">                } else {</span><a href="#l403"></a>
<span id="l404">                    builder.append(&quot;,\n&quot;);</span><a href="#l404"></a>
<span id="l405">                }</span><a href="#l405"></a>
<span id="l406"></span><a href="#l406"></a>
<span id="l407">                if (parameter instanceof Throwable) {</span><a href="#l407"></a>
<span id="l408">                    builder.append(formatThrowable((Throwable)parameter));</span><a href="#l408"></a>
<span id="l409">                } else if (parameter instanceof Certificate) {</span><a href="#l409"></a>
<span id="l410">                    builder.append(formatCertificate((Certificate)parameter));</span><a href="#l410"></a>
<span id="l411">                } else if (parameter instanceof ByteArrayInputStream) {</span><a href="#l411"></a>
<span id="l412">                    builder.append(formatByteArrayInputStream(</span><a href="#l412"></a>
<span id="l413">                        (ByteArrayInputStream)parameter));</span><a href="#l413"></a>
<span id="l414">                } else if (parameter instanceof ByteBuffer) {</span><a href="#l414"></a>
<span id="l415">                    builder.append(formatByteBuffer((ByteBuffer)parameter));</span><a href="#l415"></a>
<span id="l416">                } else if (parameter instanceof byte[]) {</span><a href="#l416"></a>
<span id="l417">                    builder.append(formatByteArrayInputStream(</span><a href="#l417"></a>
<span id="l418">                        new ByteArrayInputStream((byte[])parameter)));</span><a href="#l418"></a>
<span id="l419">                } else if (parameter instanceof Map.Entry) {</span><a href="#l419"></a>
<span id="l420">                    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l420"></a>
<span id="l421">                    Map.Entry&lt;String, ?&gt; mapParameter =</span><a href="#l421"></a>
<span id="l422">                        (Map.Entry&lt;String, ?&gt;)parameter;</span><a href="#l422"></a>
<span id="l423">                    builder.append(formatMapEntry(mapParameter));</span><a href="#l423"></a>
<span id="l424">                } else {</span><a href="#l424"></a>
<span id="l425">                    builder.append(formatObject(parameter));</span><a href="#l425"></a>
<span id="l426">                }</span><a href="#l426"></a>
<span id="l427">            }</span><a href="#l427"></a>
<span id="l428"></span><a href="#l428"></a>
<span id="l429">            return builder.toString();</span><a href="#l429"></a>
<span id="l430">        }</span><a href="#l430"></a>
<span id="l431"></span><a href="#l431"></a>
<span id="l432">        // &quot;throwable&quot;: {</span><a href="#l432"></a>
<span id="l433">        //   ...</span><a href="#l433"></a>
<span id="l434">        // }</span><a href="#l434"></a>
<span id="l435">        private static String formatThrowable(Throwable throwable) {</span><a href="#l435"></a>
<span id="l436">            StringBuilder builder = new StringBuilder(512);</span><a href="#l436"></a>
<span id="l437">            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();</span><a href="#l437"></a>
<span id="l438">            try (PrintStream out = new PrintStream(bytesOut)) {</span><a href="#l438"></a>
<span id="l439">                throwable.printStackTrace(out);</span><a href="#l439"></a>
<span id="l440">                builder.append(Utilities.indent(bytesOut.toString()));</span><a href="#l440"></a>
<span id="l441">            }</span><a href="#l441"></a>
<span id="l442">            Object[] fields = {</span><a href="#l442"></a>
<span id="l443">                    &quot;throwable&quot;,</span><a href="#l443"></a>
<span id="l444">                    builder.toString()</span><a href="#l444"></a>
<span id="l445">                };</span><a href="#l445"></a>
<span id="l446"></span><a href="#l446"></a>
<span id="l447">            return keyObjectFormat.format(fields);</span><a href="#l447"></a>
<span id="l448">        }</span><a href="#l448"></a>
<span id="l449"></span><a href="#l449"></a>
<span id="l450">        // &quot;certificate&quot;: {</span><a href="#l450"></a>
<span id="l451">        //   ...</span><a href="#l451"></a>
<span id="l452">        // }</span><a href="#l452"></a>
<span id="l453">        private static String formatCertificate(Certificate certificate) {</span><a href="#l453"></a>
<span id="l454"></span><a href="#l454"></a>
<span id="l455">            if (!(certificate instanceof X509Certificate)) {</span><a href="#l455"></a>
<span id="l456">                return Utilities.indent(certificate.toString());</span><a href="#l456"></a>
<span id="l457">            }</span><a href="#l457"></a>
<span id="l458"></span><a href="#l458"></a>
<span id="l459">            StringBuilder builder = new StringBuilder(512);</span><a href="#l459"></a>
<span id="l460">            try {</span><a href="#l460"></a>
<span id="l461">                X509CertImpl x509 =</span><a href="#l461"></a>
<span id="l462">                    X509CertImpl.toImpl((X509Certificate)certificate);</span><a href="#l462"></a>
<span id="l463">                X509CertInfo certInfo =</span><a href="#l463"></a>
<span id="l464">                        (X509CertInfo)x509.get(X509CertImpl.NAME + &quot;.&quot; +</span><a href="#l464"></a>
<span id="l465">                                                       X509CertImpl.INFO);</span><a href="#l465"></a>
<span id="l466">                CertificateExtensions certExts = (CertificateExtensions)</span><a href="#l466"></a>
<span id="l467">                        certInfo.get(X509CertInfo.EXTENSIONS);</span><a href="#l467"></a>
<span id="l468">                if (certExts == null) {</span><a href="#l468"></a>
<span id="l469">                    Object[] certFields = {</span><a href="#l469"></a>
<span id="l470">                        x509.getVersion(),</span><a href="#l470"></a>
<span id="l471">                        Utilities.toHexString(</span><a href="#l471"></a>
<span id="l472">                                x509.getSerialNumber().toByteArray()),</span><a href="#l472"></a>
<span id="l473">                        x509.getSigAlgName(),</span><a href="#l473"></a>
<span id="l474">                        x509.getIssuerX500Principal().toString(),</span><a href="#l474"></a>
<span id="l475">                        dateFormat.get().format(x509.getNotBefore()),</span><a href="#l475"></a>
<span id="l476">                        dateFormat.get().format(x509.getNotAfter()),</span><a href="#l476"></a>
<span id="l477">                        x509.getSubjectX500Principal().toString(),</span><a href="#l477"></a>
<span id="l478">                        x509.getPublicKey().getAlgorithm()</span><a href="#l478"></a>
<span id="l479">                        };</span><a href="#l479"></a>
<span id="l480">                    builder.append(Utilities.indent(</span><a href="#l480"></a>
<span id="l481">                            basicCertFormat.format(certFields)));</span><a href="#l481"></a>
<span id="l482">                } else {</span><a href="#l482"></a>
<span id="l483">                    StringBuilder extBuilder = new StringBuilder(512);</span><a href="#l483"></a>
<span id="l484">                    boolean isFirst = true;</span><a href="#l484"></a>
<span id="l485">                    for (Extension certExt : certExts.getAllExtensions()) {</span><a href="#l485"></a>
<span id="l486">                        if (isFirst) {</span><a href="#l486"></a>
<span id="l487">                            isFirst = false;</span><a href="#l487"></a>
<span id="l488">                        } else {</span><a href="#l488"></a>
<span id="l489">                            extBuilder.append(&quot;,\n&quot;);</span><a href="#l489"></a>
<span id="l490">                        }</span><a href="#l490"></a>
<span id="l491">                        extBuilder.append(&quot;{\n&quot; +</span><a href="#l491"></a>
<span id="l492">                            Utilities.indent(certExt.toString()) + &quot;\n}&quot;);</span><a href="#l492"></a>
<span id="l493">                    }</span><a href="#l493"></a>
<span id="l494">                    Object[] certFields = {</span><a href="#l494"></a>
<span id="l495">                        x509.getVersion(),</span><a href="#l495"></a>
<span id="l496">                        Utilities.toHexString(</span><a href="#l496"></a>
<span id="l497">                                x509.getSerialNumber().toByteArray()),</span><a href="#l497"></a>
<span id="l498">                        x509.getSigAlgName(),</span><a href="#l498"></a>
<span id="l499">                        x509.getIssuerX500Principal().toString(),</span><a href="#l499"></a>
<span id="l500">                        dateFormat.get().format(x509.getNotBefore()),</span><a href="#l500"></a>
<span id="l501">                        dateFormat.get().format(x509.getNotAfter()),</span><a href="#l501"></a>
<span id="l502">                        x509.getSubjectX500Principal().toString(),</span><a href="#l502"></a>
<span id="l503">                        x509.getPublicKey().getAlgorithm(),</span><a href="#l503"></a>
<span id="l504">                        Utilities.indent(extBuilder.toString())</span><a href="#l504"></a>
<span id="l505">                        };</span><a href="#l505"></a>
<span id="l506">                    builder.append(Utilities.indent(</span><a href="#l506"></a>
<span id="l507">                            extendedCertFormart.format(certFields)));</span><a href="#l507"></a>
<span id="l508">                }</span><a href="#l508"></a>
<span id="l509">            } catch (Exception ce) {</span><a href="#l509"></a>
<span id="l510">                // ignore the exception</span><a href="#l510"></a>
<span id="l511">            }</span><a href="#l511"></a>
<span id="l512"></span><a href="#l512"></a>
<span id="l513">            Object[] fields = {</span><a href="#l513"></a>
<span id="l514">                    &quot;certificate&quot;,</span><a href="#l514"></a>
<span id="l515">                    builder.toString()</span><a href="#l515"></a>
<span id="l516">                };</span><a href="#l516"></a>
<span id="l517"></span><a href="#l517"></a>
<span id="l518">            return Utilities.indent(keyObjectFormat.format(fields));</span><a href="#l518"></a>
<span id="l519">        }</span><a href="#l519"></a>
<span id="l520"></span><a href="#l520"></a>
<span id="l521">        private static String formatByteArrayInputStream(</span><a href="#l521"></a>
<span id="l522">                ByteArrayInputStream bytes) {</span><a href="#l522"></a>
<span id="l523">            StringBuilder builder = new StringBuilder(512);</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">            try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream()) {</span><a href="#l525"></a>
<span id="l526">                HexDumpEncoder hexEncoder = new HexDumpEncoder();</span><a href="#l526"></a>
<span id="l527">                hexEncoder.encodeBuffer(bytes, bytesOut);</span><a href="#l527"></a>
<span id="l528"></span><a href="#l528"></a>
<span id="l529">                builder.append(Utilities.indent(bytesOut.toString()));</span><a href="#l529"></a>
<span id="l530">            } catch (IOException ioe) {</span><a href="#l530"></a>
<span id="l531">                // ignore it, just for debugging.</span><a href="#l531"></a>
<span id="l532">            }</span><a href="#l532"></a>
<span id="l533"></span><a href="#l533"></a>
<span id="l534">            return builder.toString();</span><a href="#l534"></a>
<span id="l535">        }</span><a href="#l535"></a>
<span id="l536"></span><a href="#l536"></a>
<span id="l537">        private static String formatByteBuffer(ByteBuffer byteBuffer) {</span><a href="#l537"></a>
<span id="l538">            StringBuilder builder = new StringBuilder(512);</span><a href="#l538"></a>
<span id="l539">            try (ByteArrayOutputStream bytesOut = new ByteArrayOutputStream()) {</span><a href="#l539"></a>
<span id="l540">                HexDumpEncoder hexEncoder = new HexDumpEncoder();</span><a href="#l540"></a>
<span id="l541">                hexEncoder.encodeBuffer(byteBuffer.duplicate(), bytesOut);</span><a href="#l541"></a>
<span id="l542">                builder.append(Utilities.indent(bytesOut.toString()));</span><a href="#l542"></a>
<span id="l543">            } catch (IOException ioe) {</span><a href="#l543"></a>
<span id="l544">                // ignore it, just for debugging.</span><a href="#l544"></a>
<span id="l545">            }</span><a href="#l545"></a>
<span id="l546"></span><a href="#l546"></a>
<span id="l547">            return builder.toString();</span><a href="#l547"></a>
<span id="l548">        }</span><a href="#l548"></a>
<span id="l549"></span><a href="#l549"></a>
<span id="l550">        private static String formatMapEntry(Map.Entry&lt;String, ?&gt; entry) {</span><a href="#l550"></a>
<span id="l551">            String key = entry.getKey();</span><a href="#l551"></a>
<span id="l552">            Object value = entry.getValue();</span><a href="#l552"></a>
<span id="l553"></span><a href="#l553"></a>
<span id="l554">            String formatted;</span><a href="#l554"></a>
<span id="l555">            if (value instanceof String) {</span><a href="#l555"></a>
<span id="l556">                // &quot;key&quot;: &quot;value&quot;</span><a href="#l556"></a>
<span id="l557">                formatted = &quot;\&quot;&quot; + key + &quot;\&quot;: \&quot;&quot; + (String)value + &quot;\&quot;&quot;;</span><a href="#l557"></a>
<span id="l558">            } else if (value instanceof String[]) {</span><a href="#l558"></a>
<span id="l559">                // &quot;key&quot;: [ &quot;string a&quot;,</span><a href="#l559"></a>
<span id="l560">                //          &quot;string b&quot;,</span><a href="#l560"></a>
<span id="l561">                //          &quot;string c&quot;</span><a href="#l561"></a>
<span id="l562">                //        ]</span><a href="#l562"></a>
<span id="l563">                StringBuilder builder = new StringBuilder(512);</span><a href="#l563"></a>
<span id="l564">                String[] strings = (String[])value;</span><a href="#l564"></a>
<span id="l565">                builder.append(&quot;\&quot;&quot; + key + &quot;\&quot;: [\n&quot;);</span><a href="#l565"></a>
<span id="l566">                for (String string : strings) {</span><a href="#l566"></a>
<span id="l567">                    builder.append(&quot;      \&quot;&quot; + string + &quot;\&quot;&quot;);</span><a href="#l567"></a>
<span id="l568">                    if (string != strings[strings.length - 1]) {</span><a href="#l568"></a>
<span id="l569">                        builder.append(&quot;,&quot;);</span><a href="#l569"></a>
<span id="l570">                    }</span><a href="#l570"></a>
<span id="l571">                    builder.append(&quot;\n&quot;);</span><a href="#l571"></a>
<span id="l572">                }</span><a href="#l572"></a>
<span id="l573">                builder.append(&quot;      ]&quot;);</span><a href="#l573"></a>
<span id="l574"></span><a href="#l574"></a>
<span id="l575">                formatted = builder.toString();</span><a href="#l575"></a>
<span id="l576">            } else if (value instanceof byte[]) {</span><a href="#l576"></a>
<span id="l577">                formatted = &quot;\&quot;&quot; + key + &quot;\&quot;: \&quot;&quot; +</span><a href="#l577"></a>
<span id="l578">                    Utilities.toHexString((byte[])value) + &quot;\&quot;&quot;;</span><a href="#l578"></a>
<span id="l579">            } else if (value instanceof Byte) {</span><a href="#l579"></a>
<span id="l580">                formatted = &quot;\&quot;&quot; + key + &quot;\&quot;: \&quot;&quot; +</span><a href="#l580"></a>
<span id="l581">                    Utilities.toHexString((byte)value) + &quot;\&quot;&quot;;</span><a href="#l581"></a>
<span id="l582">            } else {</span><a href="#l582"></a>
<span id="l583">                formatted = &quot;\&quot;&quot; + key + &quot;\&quot;: &quot; +</span><a href="#l583"></a>
<span id="l584">                    &quot;\&quot;&quot; + value.toString() + &quot;\&quot;&quot;;</span><a href="#l584"></a>
<span id="l585">            }</span><a href="#l585"></a>
<span id="l586"></span><a href="#l586"></a>
<span id="l587">            return Utilities.indent(formatted);</span><a href="#l587"></a>
<span id="l588">        }</span><a href="#l588"></a>
<span id="l589"></span><a href="#l589"></a>
<span id="l590">        private static String formatObject(Object obj) {</span><a href="#l590"></a>
<span id="l591">            return obj.toString();</span><a href="#l591"></a>
<span id="l592">        }</span><a href="#l592"></a>
<span id="l593">    }</span><a href="#l593"></a>
<span id="l594">}</span><a href="#l594"></a></pre>
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

