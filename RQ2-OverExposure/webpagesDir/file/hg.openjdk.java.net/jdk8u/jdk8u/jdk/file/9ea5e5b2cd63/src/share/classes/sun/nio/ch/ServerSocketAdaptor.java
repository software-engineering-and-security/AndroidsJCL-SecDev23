<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk8u/jdk8u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk8u/jdk8u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk8u/jdk8u/jdk/static/mercurial.js"></script>

<title>jdk8u/jdk8u/jdk: 9ea5e5b2cd63 src/share/classes/sun/nio/ch/ServerSocketAdaptor.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk8u/jdk8u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/shortlog/9ea5e5b2cd63">log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/graph/9ea5e5b2cd63">graph</a></li>
<li><a href="/jdk8u/jdk8u/jdk/tags">tags</a></li>
<li><a href="/jdk8u/jdk8u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/rev/9ea5e5b2cd63">changeset</a></li>
<li><a href="/jdk8u/jdk8u/jdk/file/9ea5e5b2cd63/src/share/classes/sun/nio/ch/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk8u/jdk8u/jdk/file/tip/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">latest</a></li>
<li><a href="/jdk8u/jdk8u/jdk/diff/9ea5e5b2cd63/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">diff</a></li>
<li><a href="/jdk8u/jdk8u/jdk/comparison/9ea5e5b2cd63/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">comparison</a></li>
<li><a href="/jdk8u/jdk8u/jdk/annotate/9ea5e5b2cd63/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">annotate</a></li>
<li><a href="/jdk8u/jdk8u/jdk/log/9ea5e5b2cd63/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">file log</a></li>
<li><a href="/jdk8u/jdk8u/jdk/raw-file/9ea5e5b2cd63/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk8u/jdk8u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u">jdk8u</a> / <a href="/jdk8u/jdk8u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/nio/ch/ServerSocketAdaptor.java @ 13794:9ea5e5b2cd63</h3>

<form class="search" action="/jdk8u/jdk8u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk8u/jdk8u/jdk/help/revsets">revset expression</a>.</div>
</form>

<div class="description">8196956: (ch) More channels cleanup
8231795: Enhance datagram socket support
Reviewed-by: rriggs, prappo, bpb</div>

<table id="changesetEntry">
<tr>
 <th class="author">author</th>
 <td class="author">&#97;&#108;&#97;&#110;&#98;</td>
</tr>
<tr>
 <th class="date">date</th>
 <td class="date age">Thu, 08 Feb 2018 10:55:21 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk8u/jdk8u/jdk/file/e40ac3dcb075/src/share/classes/sun/nio/ch/ServerSocketAdaptor.java">e40ac3dcb075</a> </td>
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
<span id="l2"> * Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l26">package sun.nio.ch;</span><a href="#l26"></a>
<span id="l27"></span><a href="#l27"></a>
<span id="l28">import java.io.IOException;</span><a href="#l28"></a>
<span id="l29">import java.net.InetAddress;</span><a href="#l29"></a>
<span id="l30">import java.net.InetSocketAddress;</span><a href="#l30"></a>
<span id="l31">import java.net.ServerSocket;</span><a href="#l31"></a>
<span id="l32">import java.net.Socket;</span><a href="#l32"></a>
<span id="l33">import java.net.SocketAddress;</span><a href="#l33"></a>
<span id="l34">import java.net.SocketException;</span><a href="#l34"></a>
<span id="l35">import java.net.SocketTimeoutException;</span><a href="#l35"></a>
<span id="l36">import java.net.StandardSocketOptions;</span><a href="#l36"></a>
<span id="l37">import java.nio.channels.ClosedChannelException;</span><a href="#l37"></a>
<span id="l38">import java.nio.channels.IllegalBlockingModeException;</span><a href="#l38"></a>
<span id="l39">import java.nio.channels.NotYetBoundException;</span><a href="#l39"></a>
<span id="l40">import java.nio.channels.ServerSocketChannel;</span><a href="#l40"></a>
<span id="l41">import java.nio.channels.SocketChannel;</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43"></span><a href="#l43"></a>
<span id="l44">// Make a server-socket channel look like a server socket.</span><a href="#l44"></a>
<span id="l45">//</span><a href="#l45"></a>
<span id="l46">// The methods in this class are defined in exactly the same order as in</span><a href="#l46"></a>
<span id="l47">// java.net.ServerSocket so as to simplify tracking future changes to that</span><a href="#l47"></a>
<span id="l48">// class.</span><a href="#l48"></a>
<span id="l49">//</span><a href="#l49"></a>
<span id="l50"></span><a href="#l50"></a>
<span id="l51">class ServerSocketAdaptor                        // package-private</span><a href="#l51"></a>
<span id="l52">    extends ServerSocket</span><a href="#l52"></a>
<span id="l53">{</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    // The channel being adapted</span><a href="#l55"></a>
<span id="l56">    private final ServerSocketChannelImpl ssc;</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    // Timeout &quot;option&quot; value for accepts</span><a href="#l58"></a>
<span id="l59">    private volatile int timeout = 0;</span><a href="#l59"></a>
<span id="l60"></span><a href="#l60"></a>
<span id="l61">    public static ServerSocket create(ServerSocketChannelImpl ssc) {</span><a href="#l61"></a>
<span id="l62">        try {</span><a href="#l62"></a>
<span id="l63">            return new ServerSocketAdaptor(ssc);</span><a href="#l63"></a>
<span id="l64">        } catch (IOException x) {</span><a href="#l64"></a>
<span id="l65">            throw new Error(x);</span><a href="#l65"></a>
<span id="l66">        }</span><a href="#l66"></a>
<span id="l67">    }</span><a href="#l67"></a>
<span id="l68"></span><a href="#l68"></a>
<span id="l69">    // ## super will create a useless impl</span><a href="#l69"></a>
<span id="l70">    private ServerSocketAdaptor(ServerSocketChannelImpl ssc)</span><a href="#l70"></a>
<span id="l71">        throws IOException</span><a href="#l71"></a>
<span id="l72">    {</span><a href="#l72"></a>
<span id="l73">        this.ssc = ssc;</span><a href="#l73"></a>
<span id="l74">    }</span><a href="#l74"></a>
<span id="l75"></span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    public void bind(SocketAddress local) throws IOException {</span><a href="#l77"></a>
<span id="l78">        bind(local, 50);</span><a href="#l78"></a>
<span id="l79">    }</span><a href="#l79"></a>
<span id="l80"></span><a href="#l80"></a>
<span id="l81">    public void bind(SocketAddress local, int backlog) throws IOException {</span><a href="#l81"></a>
<span id="l82">        if (local == null)</span><a href="#l82"></a>
<span id="l83">            local = new InetSocketAddress(0);</span><a href="#l83"></a>
<span id="l84">        try {</span><a href="#l84"></a>
<span id="l85">            ssc.bind(local, backlog);</span><a href="#l85"></a>
<span id="l86">        } catch (Exception x) {</span><a href="#l86"></a>
<span id="l87">            Net.translateException(x);</span><a href="#l87"></a>
<span id="l88">        }</span><a href="#l88"></a>
<span id="l89">    }</span><a href="#l89"></a>
<span id="l90"></span><a href="#l90"></a>
<span id="l91">    public InetAddress getInetAddress() {</span><a href="#l91"></a>
<span id="l92">        if (!ssc.isBound())</span><a href="#l92"></a>
<span id="l93">            return null;</span><a href="#l93"></a>
<span id="l94">        return Net.getRevealedLocalAddress(ssc.localAddress()).getAddress();</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    }</span><a href="#l96"></a>
<span id="l97"></span><a href="#l97"></a>
<span id="l98">    public int getLocalPort() {</span><a href="#l98"></a>
<span id="l99">        if (!ssc.isBound())</span><a href="#l99"></a>
<span id="l100">            return -1;</span><a href="#l100"></a>
<span id="l101">        return Net.asInetSocketAddress(ssc.localAddress()).getPort();</span><a href="#l101"></a>
<span id="l102">    }</span><a href="#l102"></a>
<span id="l103"></span><a href="#l103"></a>
<span id="l104"></span><a href="#l104"></a>
<span id="l105">    public Socket accept() throws IOException {</span><a href="#l105"></a>
<span id="l106">        synchronized (ssc.blockingLock()) {</span><a href="#l106"></a>
<span id="l107">            if (!ssc.isBound())</span><a href="#l107"></a>
<span id="l108">                throw new IllegalBlockingModeException();</span><a href="#l108"></a>
<span id="l109">            try {</span><a href="#l109"></a>
<span id="l110">                if (timeout == 0) {</span><a href="#l110"></a>
<span id="l111">                    // for compatibility reasons: accept connection if available</span><a href="#l111"></a>
<span id="l112">                    // when configured non-blocking</span><a href="#l112"></a>
<span id="l113">                    SocketChannel sc = ssc.accept();</span><a href="#l113"></a>
<span id="l114">                    if (sc == null &amp;&amp; !ssc.isBlocking())</span><a href="#l114"></a>
<span id="l115">                        throw new IllegalBlockingModeException();</span><a href="#l115"></a>
<span id="l116">                    return sc.socket();</span><a href="#l116"></a>
<span id="l117">                }</span><a href="#l117"></a>
<span id="l118"></span><a href="#l118"></a>
<span id="l119">                if (!ssc.isBlocking())</span><a href="#l119"></a>
<span id="l120">                    throw new IllegalBlockingModeException();</span><a href="#l120"></a>
<span id="l121">                ssc.configureBlocking(false);</span><a href="#l121"></a>
<span id="l122">                try {</span><a href="#l122"></a>
<span id="l123">                    SocketChannel sc;</span><a href="#l123"></a>
<span id="l124">                    if ((sc = ssc.accept()) != null)</span><a href="#l124"></a>
<span id="l125">                        return sc.socket();</span><a href="#l125"></a>
<span id="l126">                    long to = timeout;</span><a href="#l126"></a>
<span id="l127">                    for (;;) {</span><a href="#l127"></a>
<span id="l128">                        if (!ssc.isOpen())</span><a href="#l128"></a>
<span id="l129">                            throw new ClosedChannelException();</span><a href="#l129"></a>
<span id="l130">                        long st = System.currentTimeMillis();</span><a href="#l130"></a>
<span id="l131">                        int result = ssc.poll(Net.POLLIN, to);</span><a href="#l131"></a>
<span id="l132">                        if (result &gt; 0 &amp;&amp; ((sc = ssc.accept()) != null))</span><a href="#l132"></a>
<span id="l133">                            return sc.socket();</span><a href="#l133"></a>
<span id="l134">                        to -= System.currentTimeMillis() - st;</span><a href="#l134"></a>
<span id="l135">                        if (to &lt;= 0)</span><a href="#l135"></a>
<span id="l136">                            throw new SocketTimeoutException();</span><a href="#l136"></a>
<span id="l137">                    }</span><a href="#l137"></a>
<span id="l138">                } finally {</span><a href="#l138"></a>
<span id="l139">                    try {</span><a href="#l139"></a>
<span id="l140">                        ssc.configureBlocking(true);</span><a href="#l140"></a>
<span id="l141">                    } catch (ClosedChannelException e) { }</span><a href="#l141"></a>
<span id="l142">                }</span><a href="#l142"></a>
<span id="l143">            } catch (Exception x) {</span><a href="#l143"></a>
<span id="l144">                Net.translateException(x);</span><a href="#l144"></a>
<span id="l145">                assert false;</span><a href="#l145"></a>
<span id="l146">                return null;            // Never happens</span><a href="#l146"></a>
<span id="l147">            }</span><a href="#l147"></a>
<span id="l148">        }</span><a href="#l148"></a>
<span id="l149">    }</span><a href="#l149"></a>
<span id="l150"></span><a href="#l150"></a>
<span id="l151">    public void close() throws IOException {</span><a href="#l151"></a>
<span id="l152">        ssc.close();</span><a href="#l152"></a>
<span id="l153">    }</span><a href="#l153"></a>
<span id="l154"></span><a href="#l154"></a>
<span id="l155">    public ServerSocketChannel getChannel() {</span><a href="#l155"></a>
<span id="l156">        return ssc;</span><a href="#l156"></a>
<span id="l157">    }</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">    public boolean isBound() {</span><a href="#l159"></a>
<span id="l160">        return ssc.isBound();</span><a href="#l160"></a>
<span id="l161">    }</span><a href="#l161"></a>
<span id="l162"></span><a href="#l162"></a>
<span id="l163">    public boolean isClosed() {</span><a href="#l163"></a>
<span id="l164">        return !ssc.isOpen();</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    public void setSoTimeout(int timeout) throws SocketException {</span><a href="#l167"></a>
<span id="l168">        this.timeout = timeout;</span><a href="#l168"></a>
<span id="l169">    }</span><a href="#l169"></a>
<span id="l170"></span><a href="#l170"></a>
<span id="l171">    public int getSoTimeout() throws SocketException {</span><a href="#l171"></a>
<span id="l172">        return timeout;</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    public void setReuseAddress(boolean on) throws SocketException {</span><a href="#l175"></a>
<span id="l176">        try {</span><a href="#l176"></a>
<span id="l177">            ssc.setOption(StandardSocketOptions.SO_REUSEADDR, on);</span><a href="#l177"></a>
<span id="l178">        } catch (IOException x) {</span><a href="#l178"></a>
<span id="l179">            Net.translateToSocketException(x);</span><a href="#l179"></a>
<span id="l180">        }</span><a href="#l180"></a>
<span id="l181">    }</span><a href="#l181"></a>
<span id="l182"></span><a href="#l182"></a>
<span id="l183">    public boolean getReuseAddress() throws SocketException {</span><a href="#l183"></a>
<span id="l184">        try {</span><a href="#l184"></a>
<span id="l185">            return ssc.getOption(StandardSocketOptions.SO_REUSEADDR).booleanValue();</span><a href="#l185"></a>
<span id="l186">        } catch (IOException x) {</span><a href="#l186"></a>
<span id="l187">            Net.translateToSocketException(x);</span><a href="#l187"></a>
<span id="l188">            return false;       // Never happens</span><a href="#l188"></a>
<span id="l189">        }</span><a href="#l189"></a>
<span id="l190">    }</span><a href="#l190"></a>
<span id="l191"></span><a href="#l191"></a>
<span id="l192">    public String toString() {</span><a href="#l192"></a>
<span id="l193">        if (!isBound())</span><a href="#l193"></a>
<span id="l194">            return &quot;ServerSocket[unbound]&quot;;</span><a href="#l194"></a>
<span id="l195">        return &quot;ServerSocket[addr=&quot; + getInetAddress() +</span><a href="#l195"></a>
<span id="l196">               &quot;,localport=&quot; + getLocalPort()  + &quot;]&quot;;</span><a href="#l196"></a>
<span id="l197">    }</span><a href="#l197"></a>
<span id="l198"></span><a href="#l198"></a>
<span id="l199">    public void setReceiveBufferSize(int size) throws SocketException {</span><a href="#l199"></a>
<span id="l200">        // size 0 valid for ServerSocketChannel, invalid for ServerSocket</span><a href="#l200"></a>
<span id="l201">        if (size &lt;= 0)</span><a href="#l201"></a>
<span id="l202">            throw new IllegalArgumentException(&quot;size cannot be 0 or negative&quot;);</span><a href="#l202"></a>
<span id="l203">        try {</span><a href="#l203"></a>
<span id="l204">            ssc.setOption(StandardSocketOptions.SO_RCVBUF, size);</span><a href="#l204"></a>
<span id="l205">        } catch (IOException x) {</span><a href="#l205"></a>
<span id="l206">            Net.translateToSocketException(x);</span><a href="#l206"></a>
<span id="l207">        }</span><a href="#l207"></a>
<span id="l208">    }</span><a href="#l208"></a>
<span id="l209"></span><a href="#l209"></a>
<span id="l210">    public int getReceiveBufferSize() throws SocketException {</span><a href="#l210"></a>
<span id="l211">        try {</span><a href="#l211"></a>
<span id="l212">            return ssc.getOption(StandardSocketOptions.SO_RCVBUF).intValue();</span><a href="#l212"></a>
<span id="l213">        } catch (IOException x) {</span><a href="#l213"></a>
<span id="l214">            Net.translateToSocketException(x);</span><a href="#l214"></a>
<span id="l215">            return -1;          // Never happens</span><a href="#l215"></a>
<span id="l216">        }</span><a href="#l216"></a>
<span id="l217">    }</span><a href="#l217"></a>
<span id="l218"></span><a href="#l218"></a>
<span id="l219">}</span><a href="#l219"></a></pre>
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

