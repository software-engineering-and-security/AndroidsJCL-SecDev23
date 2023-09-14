<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US">
<head>
<link rel="icon" href="/jdk7u/jdk7u/jdk/static/nanoduke.ico" type="image/png" />
<meta name="robots" content="index, nofollow" />
<link rel="stylesheet" href="/jdk7u/jdk7u/jdk/static/style-paper.css" type="text/css" />
<script type="text/javascript" src="/jdk7u/jdk7u/jdk/static/mercurial.js"></script>

<title>jdk7u/jdk7u/jdk: c9b0a18f082e src/share/classes/sun/nio/ch/DatagramChannelImpl.java</title>
</head>
<body>

<div class="container">
<div class="menu">
<div class="logo">
<img src="/jdk7u/jdk7u/jdk/static/duke_pipes.png" width=75 height=90 border=0 alt="Duke!" />
</div>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/shortlog/c9b0a18f082e">log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/graph/c9b0a18f082e">graph</a></li>
<li><a href="/jdk7u/jdk7u/jdk/tags">tags</a></li>
<li><a href="/jdk7u/jdk7u/jdk/branches">branches</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/rev/c9b0a18f082e">changeset</a></li>
<li><a href="/jdk7u/jdk7u/jdk/file/c9b0a18f082e/src/share/classes/sun/nio/ch/">browse</a></li>
</ul>
<ul>
<li class="active">file</li>
<li><a href="/jdk7u/jdk7u/jdk/file/tip/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">latest</a></li>
<li><a href="/jdk7u/jdk7u/jdk/diff/c9b0a18f082e/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">diff</a></li>
<li><a href="/jdk7u/jdk7u/jdk/comparison/c9b0a18f082e/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">comparison</a></li>
<li><a href="/jdk7u/jdk7u/jdk/annotate/c9b0a18f082e/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">annotate</a></li>
<li><a href="/jdk7u/jdk7u/jdk/log/c9b0a18f082e/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">file log</a></li>
<li><a href="/jdk7u/jdk7u/jdk/raw-file/c9b0a18f082e/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">raw</a></li>
</ul>
<ul>
<li><a href="/jdk7u/jdk7u/jdk/help">help</a></li>
</ul>
</div>

<div class="main">
<h2 class="breadcrumb"><a href="/">OpenJDK</a> / <a href="/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u">jdk7u</a> / <a href="/jdk7u/jdk7u/jdk">jdk</a> </h2>
<h3>view src/share/classes/sun/nio/ch/DatagramChannelImpl.java @ 8936:c9b0a18f082e</h3>

<form class="search" action="/jdk7u/jdk7u/jdk/log">

<p><input name="rev" id="search1" type="text" size="30" /></p>
<div id="hint">Find changesets by keywords (author, files, the commit message), revision
number or hash, or <a href="/jdk7u/jdk7u/jdk/help/revsets">revset expression</a>.</div>
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
 <td class="date age">Wed, 22 Jan 2020 06:51:23 +0000</td>
</tr>
<tr>
 <th class="author">parents</th>
 <td class="author"><a href="/jdk7u/jdk7u/jdk/file/ff8c7bdc6405/src/share/classes/sun/nio/ch/DatagramChannelImpl.java">ff8c7bdc6405</a> </td>
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
<span id="l2"> * Copyright (c) 2001, 2012, Oracle and/or its affiliates. All rights reserved.</span><a href="#l2"></a>
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
<span id="l28">import java.io.FileDescriptor;</span><a href="#l28"></a>
<span id="l29">import java.io.IOException;</span><a href="#l29"></a>
<span id="l30">import java.net.*;</span><a href="#l30"></a>
<span id="l31">import java.nio.ByteBuffer;</span><a href="#l31"></a>
<span id="l32">import java.nio.channels.*;</span><a href="#l32"></a>
<span id="l33">import java.nio.channels.spi.*;</span><a href="#l33"></a>
<span id="l34">import java.util.*;</span><a href="#l34"></a>
<span id="l35">import sun.net.ResourceManager;</span><a href="#l35"></a>
<span id="l36">import sun.net.ExtendedOptionsImpl;</span><a href="#l36"></a>
<span id="l37"></span><a href="#l37"></a>
<span id="l38"></span><a href="#l38"></a>
<span id="l39">/**</span><a href="#l39"></a>
<span id="l40"> * An implementation of DatagramChannels.</span><a href="#l40"></a>
<span id="l41"> */</span><a href="#l41"></a>
<span id="l42"></span><a href="#l42"></a>
<span id="l43">class DatagramChannelImpl</span><a href="#l43"></a>
<span id="l44">    extends DatagramChannel</span><a href="#l44"></a>
<span id="l45">    implements SelChImpl</span><a href="#l45"></a>
<span id="l46">{</span><a href="#l46"></a>
<span id="l47"></span><a href="#l47"></a>
<span id="l48">    // Used to make native read and write calls</span><a href="#l48"></a>
<span id="l49">    private static NativeDispatcher nd = new DatagramDispatcher();</span><a href="#l49"></a>
<span id="l50"></span><a href="#l50"></a>
<span id="l51">    // Our file descriptor</span><a href="#l51"></a>
<span id="l52">    private final FileDescriptor fd;</span><a href="#l52"></a>
<span id="l53">    private final int fdVal;</span><a href="#l53"></a>
<span id="l54"></span><a href="#l54"></a>
<span id="l55">    // The protocol family of the socket</span><a href="#l55"></a>
<span id="l56">    private final ProtocolFamily family;</span><a href="#l56"></a>
<span id="l57"></span><a href="#l57"></a>
<span id="l58">    // IDs of native threads doing reads and writes, for signalling</span><a href="#l58"></a>
<span id="l59">    private volatile long readerThread = 0;</span><a href="#l59"></a>
<span id="l60">    private volatile long writerThread = 0;</span><a href="#l60"></a>
<span id="l61"></span><a href="#l61"></a>
<span id="l62">    // Cached InetAddress and port for unconnected DatagramChannels</span><a href="#l62"></a>
<span id="l63">    // used by receive0</span><a href="#l63"></a>
<span id="l64">    private InetAddress cachedSenderInetAddress;</span><a href="#l64"></a>
<span id="l65">    private int cachedSenderPort;</span><a href="#l65"></a>
<span id="l66"></span><a href="#l66"></a>
<span id="l67">    // Lock held by current reading or connecting thread</span><a href="#l67"></a>
<span id="l68">    private final Object readLock = new Object();</span><a href="#l68"></a>
<span id="l69"></span><a href="#l69"></a>
<span id="l70">    // Lock held by current writing or connecting thread</span><a href="#l70"></a>
<span id="l71">    private final Object writeLock = new Object();</span><a href="#l71"></a>
<span id="l72"></span><a href="#l72"></a>
<span id="l73">    // Lock held by any thread that modifies the state fields declared below</span><a href="#l73"></a>
<span id="l74">    // DO NOT invoke a blocking I/O operation while holding this lock!</span><a href="#l74"></a>
<span id="l75">    private final Object stateLock = new Object();</span><a href="#l75"></a>
<span id="l76"></span><a href="#l76"></a>
<span id="l77">    // -- The following fields are protected by stateLock</span><a href="#l77"></a>
<span id="l78"></span><a href="#l78"></a>
<span id="l79">    // State (does not necessarily increase monotonically)</span><a href="#l79"></a>
<span id="l80">    private static final int ST_UNINITIALIZED = -1;</span><a href="#l80"></a>
<span id="l81">    private static final int ST_UNCONNECTED = 0;</span><a href="#l81"></a>
<span id="l82">    private static final int ST_CONNECTED = 1;</span><a href="#l82"></a>
<span id="l83">    private static final int ST_KILLED = 2;</span><a href="#l83"></a>
<span id="l84">    private int state = ST_UNINITIALIZED;</span><a href="#l84"></a>
<span id="l85"></span><a href="#l85"></a>
<span id="l86">    // Binding</span><a href="#l86"></a>
<span id="l87">    private InetSocketAddress localAddress;</span><a href="#l87"></a>
<span id="l88">    private InetSocketAddress remoteAddress;</span><a href="#l88"></a>
<span id="l89"></span><a href="#l89"></a>
<span id="l90">    // Our socket adaptor, if any</span><a href="#l90"></a>
<span id="l91">    private DatagramSocket socket;</span><a href="#l91"></a>
<span id="l92"></span><a href="#l92"></a>
<span id="l93">    // Multicast support</span><a href="#l93"></a>
<span id="l94">    private MembershipRegistry registry;</span><a href="#l94"></a>
<span id="l95"></span><a href="#l95"></a>
<span id="l96">    // set true when socket is bound and SO_REUSEADDRESS is emulated</span><a href="#l96"></a>
<span id="l97">    private boolean reuseAddressEmulated;</span><a href="#l97"></a>
<span id="l98"></span><a href="#l98"></a>
<span id="l99">    // set true/false when socket is already bound and SO_REUSEADDR is emulated</span><a href="#l99"></a>
<span id="l100">    private boolean isReuseAddress;</span><a href="#l100"></a>
<span id="l101"></span><a href="#l101"></a>
<span id="l102">    // -- End of fields protected by stateLock</span><a href="#l102"></a>
<span id="l103"></span><a href="#l103"></a>
<span id="l104">    public DatagramChannelImpl(SelectorProvider sp)</span><a href="#l104"></a>
<span id="l105">        throws IOException</span><a href="#l105"></a>
<span id="l106">    {</span><a href="#l106"></a>
<span id="l107">        super(sp);</span><a href="#l107"></a>
<span id="l108">        ResourceManager.beforeUdpCreate();</span><a href="#l108"></a>
<span id="l109">        try {</span><a href="#l109"></a>
<span id="l110">            this.family = Net.isIPv6Available() ?</span><a href="#l110"></a>
<span id="l111">                StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;</span><a href="#l111"></a>
<span id="l112">            this.fd = Net.socket(family, false);</span><a href="#l112"></a>
<span id="l113">            this.fdVal = IOUtil.fdVal(fd);</span><a href="#l113"></a>
<span id="l114">            this.state = ST_UNCONNECTED;</span><a href="#l114"></a>
<span id="l115">        } catch (IOException ioe) {</span><a href="#l115"></a>
<span id="l116">            ResourceManager.afterUdpClose();</span><a href="#l116"></a>
<span id="l117">            throw ioe;</span><a href="#l117"></a>
<span id="l118">        }</span><a href="#l118"></a>
<span id="l119">    }</span><a href="#l119"></a>
<span id="l120"></span><a href="#l120"></a>
<span id="l121">    public DatagramChannelImpl(SelectorProvider sp, ProtocolFamily family)</span><a href="#l121"></a>
<span id="l122">        throws IOException</span><a href="#l122"></a>
<span id="l123">    {</span><a href="#l123"></a>
<span id="l124">        super(sp);</span><a href="#l124"></a>
<span id="l125">        if ((family != StandardProtocolFamily.INET) &amp;&amp;</span><a href="#l125"></a>
<span id="l126">            (family != StandardProtocolFamily.INET6))</span><a href="#l126"></a>
<span id="l127">        {</span><a href="#l127"></a>
<span id="l128">            if (family == null)</span><a href="#l128"></a>
<span id="l129">                throw new NullPointerException(&quot;'family' is null&quot;);</span><a href="#l129"></a>
<span id="l130">            else</span><a href="#l130"></a>
<span id="l131">                throw new UnsupportedOperationException(&quot;Protocol family not supported&quot;);</span><a href="#l131"></a>
<span id="l132">        }</span><a href="#l132"></a>
<span id="l133">        if (family == StandardProtocolFamily.INET6) {</span><a href="#l133"></a>
<span id="l134">            if (!Net.isIPv6Available()) {</span><a href="#l134"></a>
<span id="l135">                throw new UnsupportedOperationException(&quot;IPv6 not available&quot;);</span><a href="#l135"></a>
<span id="l136">            }</span><a href="#l136"></a>
<span id="l137">        }</span><a href="#l137"></a>
<span id="l138"></span><a href="#l138"></a>
<span id="l139">        ResourceManager.beforeUdpCreate();</span><a href="#l139"></a>
<span id="l140">        try {</span><a href="#l140"></a>
<span id="l141">            this.family = family;</span><a href="#l141"></a>
<span id="l142">            this.fd = Net.socket(family, false);</span><a href="#l142"></a>
<span id="l143">            this.fdVal = IOUtil.fdVal(fd);</span><a href="#l143"></a>
<span id="l144">            this.state = ST_UNCONNECTED;</span><a href="#l144"></a>
<span id="l145">        } catch (IOException ioe) {</span><a href="#l145"></a>
<span id="l146">            ResourceManager.afterUdpClose();</span><a href="#l146"></a>
<span id="l147">            throw ioe;</span><a href="#l147"></a>
<span id="l148">        }</span><a href="#l148"></a>
<span id="l149">    }</span><a href="#l149"></a>
<span id="l150"></span><a href="#l150"></a>
<span id="l151">    public DatagramChannelImpl(SelectorProvider sp, FileDescriptor fd)</span><a href="#l151"></a>
<span id="l152">        throws IOException</span><a href="#l152"></a>
<span id="l153">    {</span><a href="#l153"></a>
<span id="l154">        super(sp);</span><a href="#l154"></a>
<span id="l155"></span><a href="#l155"></a>
<span id="l156">        // increment UDP count to match decrement when closing</span><a href="#l156"></a>
<span id="l157">        ResourceManager.beforeUdpCreate();</span><a href="#l157"></a>
<span id="l158"></span><a href="#l158"></a>
<span id="l159">        this.family = Net.isIPv6Available() ?</span><a href="#l159"></a>
<span id="l160">            StandardProtocolFamily.INET6 : StandardProtocolFamily.INET;</span><a href="#l160"></a>
<span id="l161">        this.fd = fd;</span><a href="#l161"></a>
<span id="l162">        this.fdVal = IOUtil.fdVal(fd);</span><a href="#l162"></a>
<span id="l163">        this.state = ST_UNCONNECTED;</span><a href="#l163"></a>
<span id="l164">        this.localAddress = Net.localAddress(fd);</span><a href="#l164"></a>
<span id="l165">    }</span><a href="#l165"></a>
<span id="l166"></span><a href="#l166"></a>
<span id="l167">    public DatagramSocket socket() {</span><a href="#l167"></a>
<span id="l168">        synchronized (stateLock) {</span><a href="#l168"></a>
<span id="l169">            if (socket == null)</span><a href="#l169"></a>
<span id="l170">                socket = DatagramSocketAdaptor.create(this);</span><a href="#l170"></a>
<span id="l171">            return socket;</span><a href="#l171"></a>
<span id="l172">        }</span><a href="#l172"></a>
<span id="l173">    }</span><a href="#l173"></a>
<span id="l174"></span><a href="#l174"></a>
<span id="l175">    @Override</span><a href="#l175"></a>
<span id="l176">    public SocketAddress getLocalAddress() throws IOException {</span><a href="#l176"></a>
<span id="l177">        synchronized (stateLock) {</span><a href="#l177"></a>
<span id="l178">            if (!isOpen())</span><a href="#l178"></a>
<span id="l179">                throw new ClosedChannelException();</span><a href="#l179"></a>
<span id="l180">            return Net.getRevealedLocalAddress(localAddress);</span><a href="#l180"></a>
<span id="l181">        }</span><a href="#l181"></a>
<span id="l182">    }</span><a href="#l182"></a>
<span id="l183"></span><a href="#l183"></a>
<span id="l184">    @Override</span><a href="#l184"></a>
<span id="l185">    public SocketAddress getRemoteAddress() throws IOException {</span><a href="#l185"></a>
<span id="l186">        synchronized (stateLock) {</span><a href="#l186"></a>
<span id="l187">            if (!isOpen())</span><a href="#l187"></a>
<span id="l188">                throw new ClosedChannelException();</span><a href="#l188"></a>
<span id="l189">            return remoteAddress;</span><a href="#l189"></a>
<span id="l190">        }</span><a href="#l190"></a>
<span id="l191">    }</span><a href="#l191"></a>
<span id="l192"></span><a href="#l192"></a>
<span id="l193">    @Override</span><a href="#l193"></a>
<span id="l194">    public &lt;T&gt; DatagramChannel setOption(SocketOption&lt;T&gt; name, T value)</span><a href="#l194"></a>
<span id="l195">        throws IOException</span><a href="#l195"></a>
<span id="l196">    {</span><a href="#l196"></a>
<span id="l197">        if (name == null)</span><a href="#l197"></a>
<span id="l198">            throw new NullPointerException();</span><a href="#l198"></a>
<span id="l199">        if (!supportedOptions().contains(name))</span><a href="#l199"></a>
<span id="l200">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l200"></a>
<span id="l201"></span><a href="#l201"></a>
<span id="l202">        synchronized (stateLock) {</span><a href="#l202"></a>
<span id="l203">            ensureOpen();</span><a href="#l203"></a>
<span id="l204"></span><a href="#l204"></a>
<span id="l205">            if (name == StandardSocketOptions.IP_TOS ||</span><a href="#l205"></a>
<span id="l206">                name == StandardSocketOptions.IP_MULTICAST_TTL ||</span><a href="#l206"></a>
<span id="l207">                name == StandardSocketOptions.IP_MULTICAST_LOOP)</span><a href="#l207"></a>
<span id="l208">            {</span><a href="#l208"></a>
<span id="l209">                // options are protocol dependent</span><a href="#l209"></a>
<span id="l210">                Net.setSocketOption(fd, family, name, value);</span><a href="#l210"></a>
<span id="l211">                return this;</span><a href="#l211"></a>
<span id="l212">            }</span><a href="#l212"></a>
<span id="l213"></span><a href="#l213"></a>
<span id="l214">            if (name == StandardSocketOptions.IP_MULTICAST_IF) {</span><a href="#l214"></a>
<span id="l215">                if (value == null)</span><a href="#l215"></a>
<span id="l216">                    throw new IllegalArgumentException(&quot;Cannot set IP_MULTICAST_IF to 'null'&quot;);</span><a href="#l216"></a>
<span id="l217">                NetworkInterface interf = (NetworkInterface)value;</span><a href="#l217"></a>
<span id="l218">                if (family == StandardProtocolFamily.INET6) {</span><a href="#l218"></a>
<span id="l219">                    int index = interf.getIndex();</span><a href="#l219"></a>
<span id="l220">                    if (index == -1)</span><a href="#l220"></a>
<span id="l221">                        throw new IOException(&quot;Network interface cannot be identified&quot;);</span><a href="#l221"></a>
<span id="l222">                    Net.setInterface6(fd, index);</span><a href="#l222"></a>
<span id="l223">                } else {</span><a href="#l223"></a>
<span id="l224">                    // need IPv4 address to identify interface</span><a href="#l224"></a>
<span id="l225">                    Inet4Address target = Net.anyInet4Address(interf);</span><a href="#l225"></a>
<span id="l226">                    if (target == null)</span><a href="#l226"></a>
<span id="l227">                        throw new IOException(&quot;Network interface not configured for IPv4&quot;);</span><a href="#l227"></a>
<span id="l228">                    int targetAddress = Net.inet4AsInt(target);</span><a href="#l228"></a>
<span id="l229">                    Net.setInterface4(fd, targetAddress);</span><a href="#l229"></a>
<span id="l230">                }</span><a href="#l230"></a>
<span id="l231">                return this;</span><a href="#l231"></a>
<span id="l232">            }</span><a href="#l232"></a>
<span id="l233">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp;</span><a href="#l233"></a>
<span id="l234">                    Net.useExclusiveBind() &amp;&amp; localAddress != null)</span><a href="#l234"></a>
<span id="l235">            {</span><a href="#l235"></a>
<span id="l236">                reuseAddressEmulated = true;</span><a href="#l236"></a>
<span id="l237">                this.isReuseAddress = (Boolean)value;</span><a href="#l237"></a>
<span id="l238">            }</span><a href="#l238"></a>
<span id="l239"></span><a href="#l239"></a>
<span id="l240">            // remaining options don't need any special handling</span><a href="#l240"></a>
<span id="l241">            Net.setSocketOption(fd, Net.UNSPEC, name, value);</span><a href="#l241"></a>
<span id="l242">            return this;</span><a href="#l242"></a>
<span id="l243">        }</span><a href="#l243"></a>
<span id="l244">    }</span><a href="#l244"></a>
<span id="l245"></span><a href="#l245"></a>
<span id="l246">    @Override</span><a href="#l246"></a>
<span id="l247">    @SuppressWarnings(&quot;unchecked&quot;)</span><a href="#l247"></a>
<span id="l248">    public &lt;T&gt; T getOption(SocketOption&lt;T&gt; name)</span><a href="#l248"></a>
<span id="l249">        throws IOException</span><a href="#l249"></a>
<span id="l250">    {</span><a href="#l250"></a>
<span id="l251">        if (name == null)</span><a href="#l251"></a>
<span id="l252">            throw new NullPointerException();</span><a href="#l252"></a>
<span id="l253">        if (!supportedOptions().contains(name))</span><a href="#l253"></a>
<span id="l254">            throw new UnsupportedOperationException(&quot;'&quot; + name + &quot;' not supported&quot;);</span><a href="#l254"></a>
<span id="l255"></span><a href="#l255"></a>
<span id="l256">        synchronized (stateLock) {</span><a href="#l256"></a>
<span id="l257">            ensureOpen();</span><a href="#l257"></a>
<span id="l258"></span><a href="#l258"></a>
<span id="l259">            if (name == StandardSocketOptions.IP_TOS ||</span><a href="#l259"></a>
<span id="l260">                name == StandardSocketOptions.IP_MULTICAST_TTL ||</span><a href="#l260"></a>
<span id="l261">                name == StandardSocketOptions.IP_MULTICAST_LOOP)</span><a href="#l261"></a>
<span id="l262">            {</span><a href="#l262"></a>
<span id="l263">                return (T) Net.getSocketOption(fd, family, name);</span><a href="#l263"></a>
<span id="l264">            }</span><a href="#l264"></a>
<span id="l265"></span><a href="#l265"></a>
<span id="l266">            if (name == StandardSocketOptions.IP_MULTICAST_IF) {</span><a href="#l266"></a>
<span id="l267">                if (family == StandardProtocolFamily.INET) {</span><a href="#l267"></a>
<span id="l268">                    int address = Net.getInterface4(fd);</span><a href="#l268"></a>
<span id="l269">                    if (address == 0)</span><a href="#l269"></a>
<span id="l270">                        return null;    // default interface</span><a href="#l270"></a>
<span id="l271"></span><a href="#l271"></a>
<span id="l272">                    InetAddress ia = Net.inet4FromInt(address);</span><a href="#l272"></a>
<span id="l273">                    NetworkInterface ni = NetworkInterface.getByInetAddress(ia);</span><a href="#l273"></a>
<span id="l274">                    if (ni == null)</span><a href="#l274"></a>
<span id="l275">                        throw new IOException(&quot;Unable to map address to interface&quot;);</span><a href="#l275"></a>
<span id="l276">                    return (T) ni;</span><a href="#l276"></a>
<span id="l277">                } else {</span><a href="#l277"></a>
<span id="l278">                    int index = Net.getInterface6(fd);</span><a href="#l278"></a>
<span id="l279">                    if (index == 0)</span><a href="#l279"></a>
<span id="l280">                        return null;    // default interface</span><a href="#l280"></a>
<span id="l281"></span><a href="#l281"></a>
<span id="l282">                    NetworkInterface ni = NetworkInterface.getByIndex(index);</span><a href="#l282"></a>
<span id="l283">                    if (ni == null)</span><a href="#l283"></a>
<span id="l284">                        throw new IOException(&quot;Unable to map index to interface&quot;);</span><a href="#l284"></a>
<span id="l285">                    return (T) ni;</span><a href="#l285"></a>
<span id="l286">                }</span><a href="#l286"></a>
<span id="l287">            }</span><a href="#l287"></a>
<span id="l288"></span><a href="#l288"></a>
<span id="l289">            if (name == StandardSocketOptions.SO_REUSEADDR &amp;&amp;</span><a href="#l289"></a>
<span id="l290">                    reuseAddressEmulated)</span><a href="#l290"></a>
<span id="l291">            {</span><a href="#l291"></a>
<span id="l292">                return (T)Boolean.valueOf(isReuseAddress);</span><a href="#l292"></a>
<span id="l293">            }</span><a href="#l293"></a>
<span id="l294"></span><a href="#l294"></a>
<span id="l295">            // no special handling</span><a href="#l295"></a>
<span id="l296">            return (T) Net.getSocketOption(fd, Net.UNSPEC, name);</span><a href="#l296"></a>
<span id="l297">        }</span><a href="#l297"></a>
<span id="l298">    }</span><a href="#l298"></a>
<span id="l299"></span><a href="#l299"></a>
<span id="l300">    private static class DefaultOptionsHolder {</span><a href="#l300"></a>
<span id="l301">        static final Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions = defaultOptions();</span><a href="#l301"></a>
<span id="l302"></span><a href="#l302"></a>
<span id="l303">        private static Set&lt;SocketOption&lt;?&gt;&gt; defaultOptions() {</span><a href="#l303"></a>
<span id="l304">            HashSet&lt;SocketOption&lt;?&gt;&gt; set = new HashSet&lt;SocketOption&lt;?&gt;&gt;(8);</span><a href="#l304"></a>
<span id="l305">            set.add(StandardSocketOptions.SO_SNDBUF);</span><a href="#l305"></a>
<span id="l306">            set.add(StandardSocketOptions.SO_RCVBUF);</span><a href="#l306"></a>
<span id="l307">            set.add(StandardSocketOptions.SO_REUSEADDR);</span><a href="#l307"></a>
<span id="l308">            set.add(StandardSocketOptions.SO_BROADCAST);</span><a href="#l308"></a>
<span id="l309">            set.add(StandardSocketOptions.IP_TOS);</span><a href="#l309"></a>
<span id="l310">            set.add(StandardSocketOptions.IP_MULTICAST_IF);</span><a href="#l310"></a>
<span id="l311">            set.add(StandardSocketOptions.IP_MULTICAST_TTL);</span><a href="#l311"></a>
<span id="l312">            set.add(StandardSocketOptions.IP_MULTICAST_LOOP);</span><a href="#l312"></a>
<span id="l313">            if (ExtendedOptionsImpl.flowSupported()) {</span><a href="#l313"></a>
<span id="l314">                set.add(jdk.net.ExtendedSocketOptions.SO_FLOW_SLA);</span><a href="#l314"></a>
<span id="l315">            }</span><a href="#l315"></a>
<span id="l316">            return Collections.unmodifiableSet(set);</span><a href="#l316"></a>
<span id="l317">        }</span><a href="#l317"></a>
<span id="l318">    }</span><a href="#l318"></a>
<span id="l319"></span><a href="#l319"></a>
<span id="l320">    @Override</span><a href="#l320"></a>
<span id="l321">    public final Set&lt;SocketOption&lt;?&gt;&gt; supportedOptions() {</span><a href="#l321"></a>
<span id="l322">        return DefaultOptionsHolder.defaultOptions;</span><a href="#l322"></a>
<span id="l323">    }</span><a href="#l323"></a>
<span id="l324"></span><a href="#l324"></a>
<span id="l325">    private void ensureOpen() throws ClosedChannelException {</span><a href="#l325"></a>
<span id="l326">        if (!isOpen())</span><a href="#l326"></a>
<span id="l327">            throw new ClosedChannelException();</span><a href="#l327"></a>
<span id="l328">    }</span><a href="#l328"></a>
<span id="l329"></span><a href="#l329"></a>
<span id="l330">    private SocketAddress sender;       // Set by receive0 (## ugh)</span><a href="#l330"></a>
<span id="l331"></span><a href="#l331"></a>
<span id="l332">    public SocketAddress receive(ByteBuffer dst) throws IOException {</span><a href="#l332"></a>
<span id="l333">        if (dst.isReadOnly())</span><a href="#l333"></a>
<span id="l334">            throw new IllegalArgumentException(&quot;Read-only buffer&quot;);</span><a href="#l334"></a>
<span id="l335">        if (dst == null)</span><a href="#l335"></a>
<span id="l336">            throw new NullPointerException();</span><a href="#l336"></a>
<span id="l337">        synchronized (readLock) {</span><a href="#l337"></a>
<span id="l338">            ensureOpen();</span><a href="#l338"></a>
<span id="l339">            // Socket was not bound before attempting receive</span><a href="#l339"></a>
<span id="l340">            if (localAddress() == null)</span><a href="#l340"></a>
<span id="l341">                bind(null);</span><a href="#l341"></a>
<span id="l342">            int n = 0;</span><a href="#l342"></a>
<span id="l343">            ByteBuffer bb = null;</span><a href="#l343"></a>
<span id="l344">            try {</span><a href="#l344"></a>
<span id="l345">                begin();</span><a href="#l345"></a>
<span id="l346">                if (!isOpen())</span><a href="#l346"></a>
<span id="l347">                    return null;</span><a href="#l347"></a>
<span id="l348">                SecurityManager security = System.getSecurityManager();</span><a href="#l348"></a>
<span id="l349">                readerThread = NativeThread.current();</span><a href="#l349"></a>
<span id="l350">                if (isConnected() || (security == null)) {</span><a href="#l350"></a>
<span id="l351">                    do {</span><a href="#l351"></a>
<span id="l352">                        n = receive(fd, dst);</span><a href="#l352"></a>
<span id="l353">                    } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l353"></a>
<span id="l354">                    if (n == IOStatus.UNAVAILABLE)</span><a href="#l354"></a>
<span id="l355">                        return null;</span><a href="#l355"></a>
<span id="l356">                } else {</span><a href="#l356"></a>
<span id="l357">                    bb = Util.getTemporaryDirectBuffer(dst.remaining());</span><a href="#l357"></a>
<span id="l358">                    for (;;) {</span><a href="#l358"></a>
<span id="l359">                        do {</span><a href="#l359"></a>
<span id="l360">                            n = receive(fd, bb);</span><a href="#l360"></a>
<span id="l361">                        } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l361"></a>
<span id="l362">                        if (n == IOStatus.UNAVAILABLE)</span><a href="#l362"></a>
<span id="l363">                            return null;</span><a href="#l363"></a>
<span id="l364">                        InetSocketAddress isa = (InetSocketAddress)sender;</span><a href="#l364"></a>
<span id="l365">                        try {</span><a href="#l365"></a>
<span id="l366">                            security.checkAccept(</span><a href="#l366"></a>
<span id="l367">                                isa.getAddress().getHostAddress(),</span><a href="#l367"></a>
<span id="l368">                                isa.getPort());</span><a href="#l368"></a>
<span id="l369">                        } catch (SecurityException se) {</span><a href="#l369"></a>
<span id="l370">                            // Ignore packet</span><a href="#l370"></a>
<span id="l371">                            bb.clear();</span><a href="#l371"></a>
<span id="l372">                            n = 0;</span><a href="#l372"></a>
<span id="l373">                            continue;</span><a href="#l373"></a>
<span id="l374">                        }</span><a href="#l374"></a>
<span id="l375">                        bb.flip();</span><a href="#l375"></a>
<span id="l376">                        dst.put(bb);</span><a href="#l376"></a>
<span id="l377">                        break;</span><a href="#l377"></a>
<span id="l378">                    }</span><a href="#l378"></a>
<span id="l379">                }</span><a href="#l379"></a>
<span id="l380">                return sender;</span><a href="#l380"></a>
<span id="l381">            } finally {</span><a href="#l381"></a>
<span id="l382">                if (bb != null)</span><a href="#l382"></a>
<span id="l383">                    Util.releaseTemporaryDirectBuffer(bb);</span><a href="#l383"></a>
<span id="l384">                readerThread = 0;</span><a href="#l384"></a>
<span id="l385">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l385"></a>
<span id="l386">                assert IOStatus.check(n);</span><a href="#l386"></a>
<span id="l387">            }</span><a href="#l387"></a>
<span id="l388">        }</span><a href="#l388"></a>
<span id="l389">    }</span><a href="#l389"></a>
<span id="l390"></span><a href="#l390"></a>
<span id="l391">    private int receive(FileDescriptor fd, ByteBuffer dst)</span><a href="#l391"></a>
<span id="l392">        throws IOException</span><a href="#l392"></a>
<span id="l393">    {</span><a href="#l393"></a>
<span id="l394">        int pos = dst.position();</span><a href="#l394"></a>
<span id="l395">        int lim = dst.limit();</span><a href="#l395"></a>
<span id="l396">        assert (pos &lt;= lim);</span><a href="#l396"></a>
<span id="l397">        int rem = (pos &lt;= lim ? lim - pos : 0);</span><a href="#l397"></a>
<span id="l398">        if (dst instanceof DirectBuffer &amp;&amp; rem &gt; 0)</span><a href="#l398"></a>
<span id="l399">            return receiveIntoNativeBuffer(fd, dst, rem, pos);</span><a href="#l399"></a>
<span id="l400"></span><a href="#l400"></a>
<span id="l401">        // Substitute a native buffer. If the supplied buffer is empty</span><a href="#l401"></a>
<span id="l402">        // we must instead use a nonempty buffer, otherwise the call</span><a href="#l402"></a>
<span id="l403">        // will not block waiting for a datagram on some platforms.</span><a href="#l403"></a>
<span id="l404">        int newSize = Math.max(rem, 1);</span><a href="#l404"></a>
<span id="l405">        ByteBuffer bb = Util.getTemporaryDirectBuffer(newSize);</span><a href="#l405"></a>
<span id="l406">        try {</span><a href="#l406"></a>
<span id="l407">            int n = receiveIntoNativeBuffer(fd, bb, newSize, 0);</span><a href="#l407"></a>
<span id="l408">            bb.flip();</span><a href="#l408"></a>
<span id="l409">            if (n &gt; 0 &amp;&amp; rem &gt; 0)</span><a href="#l409"></a>
<span id="l410">                dst.put(bb);</span><a href="#l410"></a>
<span id="l411">            return n;</span><a href="#l411"></a>
<span id="l412">        } finally {</span><a href="#l412"></a>
<span id="l413">            Util.releaseTemporaryDirectBuffer(bb);</span><a href="#l413"></a>
<span id="l414">        }</span><a href="#l414"></a>
<span id="l415">    }</span><a href="#l415"></a>
<span id="l416"></span><a href="#l416"></a>
<span id="l417">    private int receiveIntoNativeBuffer(FileDescriptor fd, ByteBuffer bb,</span><a href="#l417"></a>
<span id="l418">                                        int rem, int pos)</span><a href="#l418"></a>
<span id="l419">        throws IOException</span><a href="#l419"></a>
<span id="l420">    {</span><a href="#l420"></a>
<span id="l421">        int n = receive0(fd, ((DirectBuffer)bb).address() + pos, rem,</span><a href="#l421"></a>
<span id="l422">                         isConnected());</span><a href="#l422"></a>
<span id="l423">        if (n &gt; 0)</span><a href="#l423"></a>
<span id="l424">            bb.position(pos + n);</span><a href="#l424"></a>
<span id="l425">        return n;</span><a href="#l425"></a>
<span id="l426">    }</span><a href="#l426"></a>
<span id="l427"></span><a href="#l427"></a>
<span id="l428">    public int send(ByteBuffer src, SocketAddress target)</span><a href="#l428"></a>
<span id="l429">        throws IOException</span><a href="#l429"></a>
<span id="l430">    {</span><a href="#l430"></a>
<span id="l431">        if (src == null)</span><a href="#l431"></a>
<span id="l432">            throw new NullPointerException();</span><a href="#l432"></a>
<span id="l433"></span><a href="#l433"></a>
<span id="l434">        synchronized (writeLock) {</span><a href="#l434"></a>
<span id="l435">            ensureOpen();</span><a href="#l435"></a>
<span id="l436">            InetSocketAddress isa = Net.checkAddress(target);</span><a href="#l436"></a>
<span id="l437">            InetAddress ia = isa.getAddress();</span><a href="#l437"></a>
<span id="l438">            if (ia == null)</span><a href="#l438"></a>
<span id="l439">                throw new IOException(&quot;Target address not resolved&quot;);</span><a href="#l439"></a>
<span id="l440">            synchronized (stateLock) {</span><a href="#l440"></a>
<span id="l441">                if (!isConnected()) {</span><a href="#l441"></a>
<span id="l442">                    if (target == null)</span><a href="#l442"></a>
<span id="l443">                        throw new NullPointerException();</span><a href="#l443"></a>
<span id="l444">                    SecurityManager sm = System.getSecurityManager();</span><a href="#l444"></a>
<span id="l445">                    if (sm != null) {</span><a href="#l445"></a>
<span id="l446">                        if (ia.isMulticastAddress()) {</span><a href="#l446"></a>
<span id="l447">                            sm.checkMulticast(ia);</span><a href="#l447"></a>
<span id="l448">                        } else {</span><a href="#l448"></a>
<span id="l449">                            sm.checkConnect(ia.getHostAddress(),</span><a href="#l449"></a>
<span id="l450">                                            isa.getPort());</span><a href="#l450"></a>
<span id="l451">                        }</span><a href="#l451"></a>
<span id="l452">                    }</span><a href="#l452"></a>
<span id="l453">                } else { // Connected case; Check address then write</span><a href="#l453"></a>
<span id="l454">                    if (!target.equals(remoteAddress)) {</span><a href="#l454"></a>
<span id="l455">                        throw new IllegalArgumentException(</span><a href="#l455"></a>
<span id="l456">                            &quot;Connected address not equal to target address&quot;);</span><a href="#l456"></a>
<span id="l457">                    }</span><a href="#l457"></a>
<span id="l458">                    return write(src);</span><a href="#l458"></a>
<span id="l459">                }</span><a href="#l459"></a>
<span id="l460">            }</span><a href="#l460"></a>
<span id="l461"></span><a href="#l461"></a>
<span id="l462">            int n = 0;</span><a href="#l462"></a>
<span id="l463">            try {</span><a href="#l463"></a>
<span id="l464">                begin();</span><a href="#l464"></a>
<span id="l465">                if (!isOpen())</span><a href="#l465"></a>
<span id="l466">                    return 0;</span><a href="#l466"></a>
<span id="l467">                writerThread = NativeThread.current();</span><a href="#l467"></a>
<span id="l468">                do {</span><a href="#l468"></a>
<span id="l469">                    n = send(fd, src, isa);</span><a href="#l469"></a>
<span id="l470">                } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l470"></a>
<span id="l471"></span><a href="#l471"></a>
<span id="l472">                synchronized (stateLock) {</span><a href="#l472"></a>
<span id="l473">                    if (isOpen() &amp;&amp; (localAddress == null)) {</span><a href="#l473"></a>
<span id="l474">                        localAddress = Net.localAddress(fd);</span><a href="#l474"></a>
<span id="l475">                    }</span><a href="#l475"></a>
<span id="l476">                }</span><a href="#l476"></a>
<span id="l477">                return IOStatus.normalize(n);</span><a href="#l477"></a>
<span id="l478">            } finally {</span><a href="#l478"></a>
<span id="l479">                writerThread = 0;</span><a href="#l479"></a>
<span id="l480">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l480"></a>
<span id="l481">                assert IOStatus.check(n);</span><a href="#l481"></a>
<span id="l482">            }</span><a href="#l482"></a>
<span id="l483">        }</span><a href="#l483"></a>
<span id="l484">    }</span><a href="#l484"></a>
<span id="l485"></span><a href="#l485"></a>
<span id="l486">    private int send(FileDescriptor fd, ByteBuffer src, InetSocketAddress target)</span><a href="#l486"></a>
<span id="l487">        throws IOException</span><a href="#l487"></a>
<span id="l488">    {</span><a href="#l488"></a>
<span id="l489">        if (src instanceof DirectBuffer)</span><a href="#l489"></a>
<span id="l490">            return sendFromNativeBuffer(fd, src, target);</span><a href="#l490"></a>
<span id="l491"></span><a href="#l491"></a>
<span id="l492">        // Substitute a native buffer</span><a href="#l492"></a>
<span id="l493">        int pos = src.position();</span><a href="#l493"></a>
<span id="l494">        int lim = src.limit();</span><a href="#l494"></a>
<span id="l495">        assert (pos &lt;= lim);</span><a href="#l495"></a>
<span id="l496">        int rem = (pos &lt;= lim ? lim - pos : 0);</span><a href="#l496"></a>
<span id="l497"></span><a href="#l497"></a>
<span id="l498">        ByteBuffer bb = Util.getTemporaryDirectBuffer(rem);</span><a href="#l498"></a>
<span id="l499">        try {</span><a href="#l499"></a>
<span id="l500">            bb.put(src);</span><a href="#l500"></a>
<span id="l501">            bb.flip();</span><a href="#l501"></a>
<span id="l502">            // Do not update src until we see how many bytes were written</span><a href="#l502"></a>
<span id="l503">            src.position(pos);</span><a href="#l503"></a>
<span id="l504"></span><a href="#l504"></a>
<span id="l505">            int n = sendFromNativeBuffer(fd, bb, target);</span><a href="#l505"></a>
<span id="l506">            if (n &gt; 0) {</span><a href="#l506"></a>
<span id="l507">                // now update src</span><a href="#l507"></a>
<span id="l508">                src.position(pos + n);</span><a href="#l508"></a>
<span id="l509">            }</span><a href="#l509"></a>
<span id="l510">            return n;</span><a href="#l510"></a>
<span id="l511">        } finally {</span><a href="#l511"></a>
<span id="l512">            Util.releaseTemporaryDirectBuffer(bb);</span><a href="#l512"></a>
<span id="l513">        }</span><a href="#l513"></a>
<span id="l514">    }</span><a href="#l514"></a>
<span id="l515"></span><a href="#l515"></a>
<span id="l516">    private int sendFromNativeBuffer(FileDescriptor fd, ByteBuffer bb,</span><a href="#l516"></a>
<span id="l517">                                     InetSocketAddress target)</span><a href="#l517"></a>
<span id="l518">        throws IOException</span><a href="#l518"></a>
<span id="l519">    {</span><a href="#l519"></a>
<span id="l520">        int pos = bb.position();</span><a href="#l520"></a>
<span id="l521">        int lim = bb.limit();</span><a href="#l521"></a>
<span id="l522">        assert (pos &lt;= lim);</span><a href="#l522"></a>
<span id="l523">        int rem = (pos &lt;= lim ? lim - pos : 0);</span><a href="#l523"></a>
<span id="l524"></span><a href="#l524"></a>
<span id="l525">        boolean preferIPv6 = (family != StandardProtocolFamily.INET);</span><a href="#l525"></a>
<span id="l526">        int written;</span><a href="#l526"></a>
<span id="l527">        try {</span><a href="#l527"></a>
<span id="l528">            written = send0(preferIPv6, fd, ((DirectBuffer)bb).address() + pos,</span><a href="#l528"></a>
<span id="l529">                            rem, target.getAddress(), target.getPort());</span><a href="#l529"></a>
<span id="l530">        } catch (PortUnreachableException pue) {</span><a href="#l530"></a>
<span id="l531">            if (isConnected())</span><a href="#l531"></a>
<span id="l532">                throw pue;</span><a href="#l532"></a>
<span id="l533">            written = rem;</span><a href="#l533"></a>
<span id="l534">        }</span><a href="#l534"></a>
<span id="l535">        if (written &gt; 0)</span><a href="#l535"></a>
<span id="l536">            bb.position(pos + written);</span><a href="#l536"></a>
<span id="l537">        return written;</span><a href="#l537"></a>
<span id="l538">    }</span><a href="#l538"></a>
<span id="l539"></span><a href="#l539"></a>
<span id="l540">    public int read(ByteBuffer buf) throws IOException {</span><a href="#l540"></a>
<span id="l541">        if (buf == null)</span><a href="#l541"></a>
<span id="l542">            throw new NullPointerException();</span><a href="#l542"></a>
<span id="l543">        synchronized (readLock) {</span><a href="#l543"></a>
<span id="l544">            synchronized (stateLock) {</span><a href="#l544"></a>
<span id="l545">                ensureOpen();</span><a href="#l545"></a>
<span id="l546">                if (!isConnected())</span><a href="#l546"></a>
<span id="l547">                    throw new NotYetConnectedException();</span><a href="#l547"></a>
<span id="l548">            }</span><a href="#l548"></a>
<span id="l549">            int n = 0;</span><a href="#l549"></a>
<span id="l550">            try {</span><a href="#l550"></a>
<span id="l551">                begin();</span><a href="#l551"></a>
<span id="l552">                if (!isOpen())</span><a href="#l552"></a>
<span id="l553">                    return 0;</span><a href="#l553"></a>
<span id="l554">                readerThread = NativeThread.current();</span><a href="#l554"></a>
<span id="l555">                do {</span><a href="#l555"></a>
<span id="l556">                    n = IOUtil.read(fd, buf, -1, nd);</span><a href="#l556"></a>
<span id="l557">                } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l557"></a>
<span id="l558">                return IOStatus.normalize(n);</span><a href="#l558"></a>
<span id="l559">            } finally {</span><a href="#l559"></a>
<span id="l560">                readerThread = 0;</span><a href="#l560"></a>
<span id="l561">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l561"></a>
<span id="l562">                assert IOStatus.check(n);</span><a href="#l562"></a>
<span id="l563">            }</span><a href="#l563"></a>
<span id="l564">        }</span><a href="#l564"></a>
<span id="l565">    }</span><a href="#l565"></a>
<span id="l566"></span><a href="#l566"></a>
<span id="l567">    public long read(ByteBuffer[] dsts, int offset, int length)</span><a href="#l567"></a>
<span id="l568">        throws IOException</span><a href="#l568"></a>
<span id="l569">    {</span><a href="#l569"></a>
<span id="l570">        if ((offset &lt; 0) || (length &lt; 0) || (offset &gt; dsts.length - length))</span><a href="#l570"></a>
<span id="l571">            throw new IndexOutOfBoundsException();</span><a href="#l571"></a>
<span id="l572">        synchronized (readLock) {</span><a href="#l572"></a>
<span id="l573">            synchronized (stateLock) {</span><a href="#l573"></a>
<span id="l574">                ensureOpen();</span><a href="#l574"></a>
<span id="l575">                if (!isConnected())</span><a href="#l575"></a>
<span id="l576">                    throw new NotYetConnectedException();</span><a href="#l576"></a>
<span id="l577">            }</span><a href="#l577"></a>
<span id="l578">            long n = 0;</span><a href="#l578"></a>
<span id="l579">            try {</span><a href="#l579"></a>
<span id="l580">                begin();</span><a href="#l580"></a>
<span id="l581">                if (!isOpen())</span><a href="#l581"></a>
<span id="l582">                    return 0;</span><a href="#l582"></a>
<span id="l583">                readerThread = NativeThread.current();</span><a href="#l583"></a>
<span id="l584">                do {</span><a href="#l584"></a>
<span id="l585">                    n = IOUtil.read(fd, dsts, offset, length, nd);</span><a href="#l585"></a>
<span id="l586">                } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l586"></a>
<span id="l587">                return IOStatus.normalize(n);</span><a href="#l587"></a>
<span id="l588">            } finally {</span><a href="#l588"></a>
<span id="l589">                readerThread = 0;</span><a href="#l589"></a>
<span id="l590">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l590"></a>
<span id="l591">                assert IOStatus.check(n);</span><a href="#l591"></a>
<span id="l592">            }</span><a href="#l592"></a>
<span id="l593">        }</span><a href="#l593"></a>
<span id="l594">    }</span><a href="#l594"></a>
<span id="l595"></span><a href="#l595"></a>
<span id="l596">    public int write(ByteBuffer buf) throws IOException {</span><a href="#l596"></a>
<span id="l597">        if (buf == null)</span><a href="#l597"></a>
<span id="l598">            throw new NullPointerException();</span><a href="#l598"></a>
<span id="l599">        synchronized (writeLock) {</span><a href="#l599"></a>
<span id="l600">            synchronized (stateLock) {</span><a href="#l600"></a>
<span id="l601">                ensureOpen();</span><a href="#l601"></a>
<span id="l602">                if (!isConnected())</span><a href="#l602"></a>
<span id="l603">                    throw new NotYetConnectedException();</span><a href="#l603"></a>
<span id="l604">            }</span><a href="#l604"></a>
<span id="l605">            int n = 0;</span><a href="#l605"></a>
<span id="l606">            try {</span><a href="#l606"></a>
<span id="l607">                begin();</span><a href="#l607"></a>
<span id="l608">                if (!isOpen())</span><a href="#l608"></a>
<span id="l609">                    return 0;</span><a href="#l609"></a>
<span id="l610">                writerThread = NativeThread.current();</span><a href="#l610"></a>
<span id="l611">                do {</span><a href="#l611"></a>
<span id="l612">                    n = IOUtil.write(fd, buf, -1, nd);</span><a href="#l612"></a>
<span id="l613">                } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l613"></a>
<span id="l614">                return IOStatus.normalize(n);</span><a href="#l614"></a>
<span id="l615">            } finally {</span><a href="#l615"></a>
<span id="l616">                writerThread = 0;</span><a href="#l616"></a>
<span id="l617">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l617"></a>
<span id="l618">                assert IOStatus.check(n);</span><a href="#l618"></a>
<span id="l619">            }</span><a href="#l619"></a>
<span id="l620">        }</span><a href="#l620"></a>
<span id="l621">    }</span><a href="#l621"></a>
<span id="l622"></span><a href="#l622"></a>
<span id="l623">    public long write(ByteBuffer[] srcs, int offset, int length)</span><a href="#l623"></a>
<span id="l624">        throws IOException</span><a href="#l624"></a>
<span id="l625">    {</span><a href="#l625"></a>
<span id="l626">        if ((offset &lt; 0) || (length &lt; 0) || (offset &gt; srcs.length - length))</span><a href="#l626"></a>
<span id="l627">            throw new IndexOutOfBoundsException();</span><a href="#l627"></a>
<span id="l628">        synchronized (writeLock) {</span><a href="#l628"></a>
<span id="l629">            synchronized (stateLock) {</span><a href="#l629"></a>
<span id="l630">                ensureOpen();</span><a href="#l630"></a>
<span id="l631">                if (!isConnected())</span><a href="#l631"></a>
<span id="l632">                    throw new NotYetConnectedException();</span><a href="#l632"></a>
<span id="l633">            }</span><a href="#l633"></a>
<span id="l634">            long n = 0;</span><a href="#l634"></a>
<span id="l635">            try {</span><a href="#l635"></a>
<span id="l636">                begin();</span><a href="#l636"></a>
<span id="l637">                if (!isOpen())</span><a href="#l637"></a>
<span id="l638">                    return 0;</span><a href="#l638"></a>
<span id="l639">                writerThread = NativeThread.current();</span><a href="#l639"></a>
<span id="l640">                do {</span><a href="#l640"></a>
<span id="l641">                    n = IOUtil.write(fd, srcs, offset, length, nd);</span><a href="#l641"></a>
<span id="l642">                } while ((n == IOStatus.INTERRUPTED) &amp;&amp; isOpen());</span><a href="#l642"></a>
<span id="l643">                return IOStatus.normalize(n);</span><a href="#l643"></a>
<span id="l644">            } finally {</span><a href="#l644"></a>
<span id="l645">                writerThread = 0;</span><a href="#l645"></a>
<span id="l646">                end((n &gt; 0) || (n == IOStatus.UNAVAILABLE));</span><a href="#l646"></a>
<span id="l647">                assert IOStatus.check(n);</span><a href="#l647"></a>
<span id="l648">            }</span><a href="#l648"></a>
<span id="l649">        }</span><a href="#l649"></a>
<span id="l650">    }</span><a href="#l650"></a>
<span id="l651"></span><a href="#l651"></a>
<span id="l652">    protected void implConfigureBlocking(boolean block) throws IOException {</span><a href="#l652"></a>
<span id="l653">        IOUtil.configureBlocking(fd, block);</span><a href="#l653"></a>
<span id="l654">    }</span><a href="#l654"></a>
<span id="l655"></span><a href="#l655"></a>
<span id="l656">    public SocketAddress localAddress() {</span><a href="#l656"></a>
<span id="l657">        synchronized (stateLock) {</span><a href="#l657"></a>
<span id="l658">            return localAddress;</span><a href="#l658"></a>
<span id="l659">        }</span><a href="#l659"></a>
<span id="l660">    }</span><a href="#l660"></a>
<span id="l661"></span><a href="#l661"></a>
<span id="l662">    public SocketAddress remoteAddress() {</span><a href="#l662"></a>
<span id="l663">        synchronized (stateLock) {</span><a href="#l663"></a>
<span id="l664">            return remoteAddress;</span><a href="#l664"></a>
<span id="l665">        }</span><a href="#l665"></a>
<span id="l666">    }</span><a href="#l666"></a>
<span id="l667"></span><a href="#l667"></a>
<span id="l668">    @Override</span><a href="#l668"></a>
<span id="l669">    public DatagramChannel bind(SocketAddress local) throws IOException {</span><a href="#l669"></a>
<span id="l670">        synchronized (readLock) {</span><a href="#l670"></a>
<span id="l671">            synchronized (writeLock) {</span><a href="#l671"></a>
<span id="l672">                synchronized (stateLock) {</span><a href="#l672"></a>
<span id="l673">                    ensureOpen();</span><a href="#l673"></a>
<span id="l674">                    if (localAddress != null)</span><a href="#l674"></a>
<span id="l675">                        throw new AlreadyBoundException();</span><a href="#l675"></a>
<span id="l676">                    InetSocketAddress isa;</span><a href="#l676"></a>
<span id="l677">                    if (local == null) {</span><a href="#l677"></a>
<span id="l678">                        // only Inet4Address allowed with IPv4 socket</span><a href="#l678"></a>
<span id="l679">                        if (family == StandardProtocolFamily.INET) {</span><a href="#l679"></a>
<span id="l680">                            isa = new InetSocketAddress(InetAddress.getByName(&quot;0.0.0.0&quot;), 0);</span><a href="#l680"></a>
<span id="l681">                        } else {</span><a href="#l681"></a>
<span id="l682">                            isa = new InetSocketAddress(0);</span><a href="#l682"></a>
<span id="l683">                        }</span><a href="#l683"></a>
<span id="l684">                    } else {</span><a href="#l684"></a>
<span id="l685">                        isa = Net.checkAddress(local);</span><a href="#l685"></a>
<span id="l686"></span><a href="#l686"></a>
<span id="l687">                        // only Inet4Address allowed with IPv4 socket</span><a href="#l687"></a>
<span id="l688">                        if (family == StandardProtocolFamily.INET) {</span><a href="#l688"></a>
<span id="l689">                            InetAddress addr = isa.getAddress();</span><a href="#l689"></a>
<span id="l690">                            if (!(addr instanceof Inet4Address))</span><a href="#l690"></a>
<span id="l691">                                throw new UnsupportedAddressTypeException();</span><a href="#l691"></a>
<span id="l692">                        }</span><a href="#l692"></a>
<span id="l693">                    }</span><a href="#l693"></a>
<span id="l694">                    SecurityManager sm = System.getSecurityManager();</span><a href="#l694"></a>
<span id="l695">                    if (sm != null) {</span><a href="#l695"></a>
<span id="l696">                        sm.checkListen(isa.getPort());</span><a href="#l696"></a>
<span id="l697">                    }</span><a href="#l697"></a>
<span id="l698">                    Net.bind(family, fd, isa.getAddress(), isa.getPort());</span><a href="#l698"></a>
<span id="l699">                    localAddress = Net.localAddress(fd);</span><a href="#l699"></a>
<span id="l700">                }</span><a href="#l700"></a>
<span id="l701">            }</span><a href="#l701"></a>
<span id="l702">        }</span><a href="#l702"></a>
<span id="l703">        return this;</span><a href="#l703"></a>
<span id="l704">    }</span><a href="#l704"></a>
<span id="l705"></span><a href="#l705"></a>
<span id="l706">    public boolean isConnected() {</span><a href="#l706"></a>
<span id="l707">        synchronized (stateLock) {</span><a href="#l707"></a>
<span id="l708">            return (state == ST_CONNECTED);</span><a href="#l708"></a>
<span id="l709">        }</span><a href="#l709"></a>
<span id="l710">    }</span><a href="#l710"></a>
<span id="l711"></span><a href="#l711"></a>
<span id="l712">    void ensureOpenAndUnconnected() throws IOException { // package-private</span><a href="#l712"></a>
<span id="l713">        synchronized (stateLock) {</span><a href="#l713"></a>
<span id="l714">            if (!isOpen())</span><a href="#l714"></a>
<span id="l715">                throw new ClosedChannelException();</span><a href="#l715"></a>
<span id="l716">            if (state != ST_UNCONNECTED)</span><a href="#l716"></a>
<span id="l717">                throw new IllegalStateException(&quot;Connect already invoked&quot;);</span><a href="#l717"></a>
<span id="l718">        }</span><a href="#l718"></a>
<span id="l719">    }</span><a href="#l719"></a>
<span id="l720"></span><a href="#l720"></a>
<span id="l721">    @Override</span><a href="#l721"></a>
<span id="l722">    public DatagramChannel connect(SocketAddress sa) throws IOException {</span><a href="#l722"></a>
<span id="l723">        int localPort = 0;</span><a href="#l723"></a>
<span id="l724"></span><a href="#l724"></a>
<span id="l725">        synchronized(readLock) {</span><a href="#l725"></a>
<span id="l726">            synchronized(writeLock) {</span><a href="#l726"></a>
<span id="l727">                synchronized (stateLock) {</span><a href="#l727"></a>
<span id="l728">                    ensureOpenAndUnconnected();</span><a href="#l728"></a>
<span id="l729">                    InetSocketAddress isa = Net.checkAddress(sa);</span><a href="#l729"></a>
<span id="l730">                    SecurityManager sm = System.getSecurityManager();</span><a href="#l730"></a>
<span id="l731">                    if (sm != null)</span><a href="#l731"></a>
<span id="l732">                        sm.checkConnect(isa.getAddress().getHostAddress(),</span><a href="#l732"></a>
<span id="l733">                                        isa.getPort());</span><a href="#l733"></a>
<span id="l734">                    int n = Net.connect(family,</span><a href="#l734"></a>
<span id="l735">                                        fd,</span><a href="#l735"></a>
<span id="l736">                                        isa.getAddress(),</span><a href="#l736"></a>
<span id="l737">                                        isa.getPort());</span><a href="#l737"></a>
<span id="l738">                    if (n &lt;= 0)</span><a href="#l738"></a>
<span id="l739">                        throw new Error();      // Can't happen</span><a href="#l739"></a>
<span id="l740"></span><a href="#l740"></a>
<span id="l741">                    // Connection succeeded; disallow further invocation</span><a href="#l741"></a>
<span id="l742">                    state = ST_CONNECTED;</span><a href="#l742"></a>
<span id="l743">                    remoteAddress = isa;</span><a href="#l743"></a>
<span id="l744">                    sender = isa;</span><a href="#l744"></a>
<span id="l745">                    cachedSenderInetAddress = isa.getAddress();</span><a href="#l745"></a>
<span id="l746">                    cachedSenderPort = isa.getPort();</span><a href="#l746"></a>
<span id="l747"></span><a href="#l747"></a>
<span id="l748">                    // set or refresh local address</span><a href="#l748"></a>
<span id="l749">                    localAddress = Net.localAddress(fd);</span><a href="#l749"></a>
<span id="l750"></span><a href="#l750"></a>
<span id="l751">                    // flush any packets already received.</span><a href="#l751"></a>
<span id="l752">                    synchronized (blockingLock()) {</span><a href="#l752"></a>
<span id="l753">                        boolean blocking = isBlocking();</span><a href="#l753"></a>
<span id="l754">                        try {</span><a href="#l754"></a>
<span id="l755">                            // remainder of each packet thrown away</span><a href="#l755"></a>
<span id="l756">                            ByteBuffer tmpBuf = ByteBuffer.allocate(1);</span><a href="#l756"></a>
<span id="l757">                            if (blocking) {</span><a href="#l757"></a>
<span id="l758">                                configureBlocking(false);</span><a href="#l758"></a>
<span id="l759">                            }</span><a href="#l759"></a>
<span id="l760">                            do {</span><a href="#l760"></a>
<span id="l761">                                tmpBuf.clear();</span><a href="#l761"></a>
<span id="l762">                            } while (receive(tmpBuf) != null);</span><a href="#l762"></a>
<span id="l763">                        } finally {</span><a href="#l763"></a>
<span id="l764">                            if (blocking) {</span><a href="#l764"></a>
<span id="l765">                                configureBlocking(true);</span><a href="#l765"></a>
<span id="l766">                            }</span><a href="#l766"></a>
<span id="l767">                        }</span><a href="#l767"></a>
<span id="l768">                    }</span><a href="#l768"></a>
<span id="l769">                }</span><a href="#l769"></a>
<span id="l770">            }</span><a href="#l770"></a>
<span id="l771">        }</span><a href="#l771"></a>
<span id="l772">        return this;</span><a href="#l772"></a>
<span id="l773">    }</span><a href="#l773"></a>
<span id="l774"></span><a href="#l774"></a>
<span id="l775">    public DatagramChannel disconnect() throws IOException {</span><a href="#l775"></a>
<span id="l776">        synchronized(readLock) {</span><a href="#l776"></a>
<span id="l777">            synchronized(writeLock) {</span><a href="#l777"></a>
<span id="l778">                synchronized (stateLock) {</span><a href="#l778"></a>
<span id="l779">                    if (!isConnected() || !isOpen())</span><a href="#l779"></a>
<span id="l780">                        return this;</span><a href="#l780"></a>
<span id="l781">                    InetSocketAddress isa = remoteAddress;</span><a href="#l781"></a>
<span id="l782">                    SecurityManager sm = System.getSecurityManager();</span><a href="#l782"></a>
<span id="l783">                    if (sm != null)</span><a href="#l783"></a>
<span id="l784">                        sm.checkConnect(isa.getAddress().getHostAddress(),</span><a href="#l784"></a>
<span id="l785">                                        isa.getPort());</span><a href="#l785"></a>
<span id="l786">                    boolean isIPv6 = (family == StandardProtocolFamily.INET6);</span><a href="#l786"></a>
<span id="l787">                    disconnect0(fd, isIPv6);</span><a href="#l787"></a>
<span id="l788">                    remoteAddress = null;</span><a href="#l788"></a>
<span id="l789">                    state = ST_UNCONNECTED;</span><a href="#l789"></a>
<span id="l790"></span><a href="#l790"></a>
<span id="l791">                    // refresh local address</span><a href="#l791"></a>
<span id="l792">                    localAddress = Net.localAddress(fd);</span><a href="#l792"></a>
<span id="l793">                }</span><a href="#l793"></a>
<span id="l794">            }</span><a href="#l794"></a>
<span id="l795">        }</span><a href="#l795"></a>
<span id="l796">        return this;</span><a href="#l796"></a>
<span id="l797">    }</span><a href="#l797"></a>
<span id="l798"></span><a href="#l798"></a>
<span id="l799">    /**</span><a href="#l799"></a>
<span id="l800">     * Joins channel's socket to the given group/interface and</span><a href="#l800"></a>
<span id="l801">     * optional source address.</span><a href="#l801"></a>
<span id="l802">     */</span><a href="#l802"></a>
<span id="l803">    private MembershipKey innerJoin(InetAddress group,</span><a href="#l803"></a>
<span id="l804">                                    NetworkInterface interf,</span><a href="#l804"></a>
<span id="l805">                                    InetAddress source)</span><a href="#l805"></a>
<span id="l806">        throws IOException</span><a href="#l806"></a>
<span id="l807">    {</span><a href="#l807"></a>
<span id="l808">        if (!group.isMulticastAddress())</span><a href="#l808"></a>
<span id="l809">            throw new IllegalArgumentException(&quot;Group not a multicast address&quot;);</span><a href="#l809"></a>
<span id="l810"></span><a href="#l810"></a>
<span id="l811">        // check multicast address is compatible with this socket</span><a href="#l811"></a>
<span id="l812">        if (group instanceof Inet4Address) {</span><a href="#l812"></a>
<span id="l813">            if (family == StandardProtocolFamily.INET6 &amp;&amp; !Net.canIPv6SocketJoinIPv4Group())</span><a href="#l813"></a>
<span id="l814">                throw new IllegalArgumentException(&quot;IPv6 socket cannot join IPv4 multicast group&quot;);</span><a href="#l814"></a>
<span id="l815">        } else if (group instanceof Inet6Address) {</span><a href="#l815"></a>
<span id="l816">            if (family != StandardProtocolFamily.INET6)</span><a href="#l816"></a>
<span id="l817">                throw new IllegalArgumentException(&quot;Only IPv6 sockets can join IPv6 multicast group&quot;);</span><a href="#l817"></a>
<span id="l818">        } else {</span><a href="#l818"></a>
<span id="l819">            throw new IllegalArgumentException(&quot;Address type not supported&quot;);</span><a href="#l819"></a>
<span id="l820">        }</span><a href="#l820"></a>
<span id="l821"></span><a href="#l821"></a>
<span id="l822">        // check source address</span><a href="#l822"></a>
<span id="l823">        if (source != null) {</span><a href="#l823"></a>
<span id="l824">            if (source.isAnyLocalAddress())</span><a href="#l824"></a>
<span id="l825">                throw new IllegalArgumentException(&quot;Source address is a wildcard address&quot;);</span><a href="#l825"></a>
<span id="l826">            if (source.isMulticastAddress())</span><a href="#l826"></a>
<span id="l827">                throw new IllegalArgumentException(&quot;Source address is multicast address&quot;);</span><a href="#l827"></a>
<span id="l828">            if (source.getClass() != group.getClass())</span><a href="#l828"></a>
<span id="l829">                throw new IllegalArgumentException(&quot;Source address is different type to group&quot;);</span><a href="#l829"></a>
<span id="l830">        }</span><a href="#l830"></a>
<span id="l831"></span><a href="#l831"></a>
<span id="l832">        SecurityManager sm = System.getSecurityManager();</span><a href="#l832"></a>
<span id="l833">        if (sm != null)</span><a href="#l833"></a>
<span id="l834">            sm.checkMulticast(group);</span><a href="#l834"></a>
<span id="l835"></span><a href="#l835"></a>
<span id="l836">        synchronized (stateLock) {</span><a href="#l836"></a>
<span id="l837">            if (!isOpen())</span><a href="#l837"></a>
<span id="l838">                throw new ClosedChannelException();</span><a href="#l838"></a>
<span id="l839"></span><a href="#l839"></a>
<span id="l840">            // check the registry to see if we are already a member of the group</span><a href="#l840"></a>
<span id="l841">            if (registry == null) {</span><a href="#l841"></a>
<span id="l842">                registry = new MembershipRegistry();</span><a href="#l842"></a>
<span id="l843">            } else {</span><a href="#l843"></a>
<span id="l844">                // return existing membership key</span><a href="#l844"></a>
<span id="l845">                MembershipKey key = registry.checkMembership(group, interf, source);</span><a href="#l845"></a>
<span id="l846">                if (key != null)</span><a href="#l846"></a>
<span id="l847">                    return key;</span><a href="#l847"></a>
<span id="l848">            }</span><a href="#l848"></a>
<span id="l849"></span><a href="#l849"></a>
<span id="l850">            MembershipKeyImpl key;</span><a href="#l850"></a>
<span id="l851">            if ((family == StandardProtocolFamily.INET6) &amp;&amp;</span><a href="#l851"></a>
<span id="l852">                ((group instanceof Inet6Address) || Net.canJoin6WithIPv4Group()))</span><a href="#l852"></a>
<span id="l853">            {</span><a href="#l853"></a>
<span id="l854">                int index = interf.getIndex();</span><a href="#l854"></a>
<span id="l855">                if (index == -1)</span><a href="#l855"></a>
<span id="l856">                    throw new IOException(&quot;Network interface cannot be identified&quot;);</span><a href="#l856"></a>
<span id="l857"></span><a href="#l857"></a>
<span id="l858">                // need multicast and source address as byte arrays</span><a href="#l858"></a>
<span id="l859">                byte[] groupAddress = Net.inet6AsByteArray(group);</span><a href="#l859"></a>
<span id="l860">                byte[] sourceAddress = (source == null) ? null :</span><a href="#l860"></a>
<span id="l861">                    Net.inet6AsByteArray(source);</span><a href="#l861"></a>
<span id="l862"></span><a href="#l862"></a>
<span id="l863">                // join the group</span><a href="#l863"></a>
<span id="l864">                int n = Net.join6(fd, groupAddress, index, sourceAddress);</span><a href="#l864"></a>
<span id="l865">                if (n == IOStatus.UNAVAILABLE)</span><a href="#l865"></a>
<span id="l866">                    throw new UnsupportedOperationException();</span><a href="#l866"></a>
<span id="l867"></span><a href="#l867"></a>
<span id="l868">                key = new MembershipKeyImpl.Type6(this, group, interf, source,</span><a href="#l868"></a>
<span id="l869">                                                  groupAddress, index, sourceAddress);</span><a href="#l869"></a>
<span id="l870"></span><a href="#l870"></a>
<span id="l871">            } else {</span><a href="#l871"></a>
<span id="l872">                // need IPv4 address to identify interface</span><a href="#l872"></a>
<span id="l873">                Inet4Address target = Net.anyInet4Address(interf);</span><a href="#l873"></a>
<span id="l874">                if (target == null)</span><a href="#l874"></a>
<span id="l875">                    throw new IOException(&quot;Network interface not configured for IPv4&quot;);</span><a href="#l875"></a>
<span id="l876"></span><a href="#l876"></a>
<span id="l877">                int groupAddress = Net.inet4AsInt(group);</span><a href="#l877"></a>
<span id="l878">                int targetAddress = Net.inet4AsInt(target);</span><a href="#l878"></a>
<span id="l879">                int sourceAddress = (source == null) ? 0 : Net.inet4AsInt(source);</span><a href="#l879"></a>
<span id="l880"></span><a href="#l880"></a>
<span id="l881">                // join the group</span><a href="#l881"></a>
<span id="l882">                int n = Net.join4(fd, groupAddress, targetAddress, sourceAddress);</span><a href="#l882"></a>
<span id="l883">                if (n == IOStatus.UNAVAILABLE)</span><a href="#l883"></a>
<span id="l884">                    throw new UnsupportedOperationException();</span><a href="#l884"></a>
<span id="l885"></span><a href="#l885"></a>
<span id="l886">                key = new MembershipKeyImpl.Type4(this, group, interf, source,</span><a href="#l886"></a>
<span id="l887">                                                  groupAddress, targetAddress, sourceAddress);</span><a href="#l887"></a>
<span id="l888">            }</span><a href="#l888"></a>
<span id="l889"></span><a href="#l889"></a>
<span id="l890">            registry.add(key);</span><a href="#l890"></a>
<span id="l891">            return key;</span><a href="#l891"></a>
<span id="l892">        }</span><a href="#l892"></a>
<span id="l893">    }</span><a href="#l893"></a>
<span id="l894"></span><a href="#l894"></a>
<span id="l895">    @Override</span><a href="#l895"></a>
<span id="l896">    public MembershipKey join(InetAddress group,</span><a href="#l896"></a>
<span id="l897">                              NetworkInterface interf)</span><a href="#l897"></a>
<span id="l898">        throws IOException</span><a href="#l898"></a>
<span id="l899">    {</span><a href="#l899"></a>
<span id="l900">        return innerJoin(group, interf, null);</span><a href="#l900"></a>
<span id="l901">    }</span><a href="#l901"></a>
<span id="l902"></span><a href="#l902"></a>
<span id="l903">    @Override</span><a href="#l903"></a>
<span id="l904">    public MembershipKey join(InetAddress group,</span><a href="#l904"></a>
<span id="l905">                              NetworkInterface interf,</span><a href="#l905"></a>
<span id="l906">                              InetAddress source)</span><a href="#l906"></a>
<span id="l907">        throws IOException</span><a href="#l907"></a>
<span id="l908">    {</span><a href="#l908"></a>
<span id="l909">        if (source == null)</span><a href="#l909"></a>
<span id="l910">            throw new NullPointerException(&quot;source address is null&quot;);</span><a href="#l910"></a>
<span id="l911">        return innerJoin(group, interf, source);</span><a href="#l911"></a>
<span id="l912">    }</span><a href="#l912"></a>
<span id="l913"></span><a href="#l913"></a>
<span id="l914">    // package-private</span><a href="#l914"></a>
<span id="l915">    void drop(MembershipKeyImpl key) {</span><a href="#l915"></a>
<span id="l916">        assert key.channel() == this;</span><a href="#l916"></a>
<span id="l917"></span><a href="#l917"></a>
<span id="l918">        synchronized (stateLock) {</span><a href="#l918"></a>
<span id="l919">            if (!key.isValid())</span><a href="#l919"></a>
<span id="l920">                return;</span><a href="#l920"></a>
<span id="l921"></span><a href="#l921"></a>
<span id="l922">            try {</span><a href="#l922"></a>
<span id="l923">                if (key instanceof MembershipKeyImpl.Type6) {</span><a href="#l923"></a>
<span id="l924">                    MembershipKeyImpl.Type6 key6 =</span><a href="#l924"></a>
<span id="l925">                        (MembershipKeyImpl.Type6)key;</span><a href="#l925"></a>
<span id="l926">                    Net.drop6(fd, key6.groupAddress(), key6.index(), key6.source());</span><a href="#l926"></a>
<span id="l927">                } else {</span><a href="#l927"></a>
<span id="l928">                    MembershipKeyImpl.Type4 key4 = (MembershipKeyImpl.Type4)key;</span><a href="#l928"></a>
<span id="l929">                    Net.drop4(fd, key4.groupAddress(), key4.interfaceAddress(),</span><a href="#l929"></a>
<span id="l930">                        key4.source());</span><a href="#l930"></a>
<span id="l931">                }</span><a href="#l931"></a>
<span id="l932">            } catch (IOException ioe) {</span><a href="#l932"></a>
<span id="l933">                // should not happen</span><a href="#l933"></a>
<span id="l934">                throw new AssertionError(ioe);</span><a href="#l934"></a>
<span id="l935">            }</span><a href="#l935"></a>
<span id="l936"></span><a href="#l936"></a>
<span id="l937">            key.invalidate();</span><a href="#l937"></a>
<span id="l938">            registry.remove(key);</span><a href="#l938"></a>
<span id="l939">        }</span><a href="#l939"></a>
<span id="l940">    }</span><a href="#l940"></a>
<span id="l941"></span><a href="#l941"></a>
<span id="l942">    /**</span><a href="#l942"></a>
<span id="l943">     * Block datagrams from given source if a memory to receive all</span><a href="#l943"></a>
<span id="l944">     * datagrams.</span><a href="#l944"></a>
<span id="l945">     */</span><a href="#l945"></a>
<span id="l946">    void block(MembershipKeyImpl key, InetAddress source)</span><a href="#l946"></a>
<span id="l947">        throws IOException</span><a href="#l947"></a>
<span id="l948">    {</span><a href="#l948"></a>
<span id="l949">        assert key.channel() == this;</span><a href="#l949"></a>
<span id="l950">        assert key.sourceAddress() == null;</span><a href="#l950"></a>
<span id="l951"></span><a href="#l951"></a>
<span id="l952">        synchronized (stateLock) {</span><a href="#l952"></a>
<span id="l953">            if (!key.isValid())</span><a href="#l953"></a>
<span id="l954">                throw new IllegalStateException(&quot;key is no longer valid&quot;);</span><a href="#l954"></a>
<span id="l955">            if (source.isAnyLocalAddress())</span><a href="#l955"></a>
<span id="l956">                throw new IllegalArgumentException(&quot;Source address is a wildcard address&quot;);</span><a href="#l956"></a>
<span id="l957">            if (source.isMulticastAddress())</span><a href="#l957"></a>
<span id="l958">                throw new IllegalArgumentException(&quot;Source address is multicast address&quot;);</span><a href="#l958"></a>
<span id="l959">            if (source.getClass() != key.group().getClass())</span><a href="#l959"></a>
<span id="l960">                throw new IllegalArgumentException(&quot;Source address is different type to group&quot;);</span><a href="#l960"></a>
<span id="l961"></span><a href="#l961"></a>
<span id="l962">            int n;</span><a href="#l962"></a>
<span id="l963">            if (key instanceof MembershipKeyImpl.Type6) {</span><a href="#l963"></a>
<span id="l964">                 MembershipKeyImpl.Type6 key6 =</span><a href="#l964"></a>
<span id="l965">                    (MembershipKeyImpl.Type6)key;</span><a href="#l965"></a>
<span id="l966">                n = Net.block6(fd, key6.groupAddress(), key6.index(),</span><a href="#l966"></a>
<span id="l967">                               Net.inet6AsByteArray(source));</span><a href="#l967"></a>
<span id="l968">            } else {</span><a href="#l968"></a>
<span id="l969">                MembershipKeyImpl.Type4 key4 =</span><a href="#l969"></a>
<span id="l970">                    (MembershipKeyImpl.Type4)key;</span><a href="#l970"></a>
<span id="l971">                n = Net.block4(fd, key4.groupAddress(), key4.interfaceAddress(),</span><a href="#l971"></a>
<span id="l972">                               Net.inet4AsInt(source));</span><a href="#l972"></a>
<span id="l973">            }</span><a href="#l973"></a>
<span id="l974">            if (n == IOStatus.UNAVAILABLE) {</span><a href="#l974"></a>
<span id="l975">                // ancient kernel</span><a href="#l975"></a>
<span id="l976">                throw new UnsupportedOperationException();</span><a href="#l976"></a>
<span id="l977">            }</span><a href="#l977"></a>
<span id="l978">        }</span><a href="#l978"></a>
<span id="l979">    }</span><a href="#l979"></a>
<span id="l980"></span><a href="#l980"></a>
<span id="l981">    /**</span><a href="#l981"></a>
<span id="l982">     * Unblock given source.</span><a href="#l982"></a>
<span id="l983">     */</span><a href="#l983"></a>
<span id="l984">    void unblock(MembershipKeyImpl key, InetAddress source) {</span><a href="#l984"></a>
<span id="l985">        assert key.channel() == this;</span><a href="#l985"></a>
<span id="l986">        assert key.sourceAddress() == null;</span><a href="#l986"></a>
<span id="l987"></span><a href="#l987"></a>
<span id="l988">        synchronized (stateLock) {</span><a href="#l988"></a>
<span id="l989">            if (!key.isValid())</span><a href="#l989"></a>
<span id="l990">                throw new IllegalStateException(&quot;key is no longer valid&quot;);</span><a href="#l990"></a>
<span id="l991"></span><a href="#l991"></a>
<span id="l992">            try {</span><a href="#l992"></a>
<span id="l993">                if (key instanceof MembershipKeyImpl.Type6) {</span><a href="#l993"></a>
<span id="l994">                    MembershipKeyImpl.Type6 key6 =</span><a href="#l994"></a>
<span id="l995">                        (MembershipKeyImpl.Type6)key;</span><a href="#l995"></a>
<span id="l996">                    Net.unblock6(fd, key6.groupAddress(), key6.index(),</span><a href="#l996"></a>
<span id="l997">                                 Net.inet6AsByteArray(source));</span><a href="#l997"></a>
<span id="l998">                } else {</span><a href="#l998"></a>
<span id="l999">                    MembershipKeyImpl.Type4 key4 =</span><a href="#l999"></a>
<span id="l1000">                        (MembershipKeyImpl.Type4)key;</span><a href="#l1000"></a>
<span id="l1001">                    Net.unblock4(fd, key4.groupAddress(), key4.interfaceAddress(),</span><a href="#l1001"></a>
<span id="l1002">                                 Net.inet4AsInt(source));</span><a href="#l1002"></a>
<span id="l1003">                }</span><a href="#l1003"></a>
<span id="l1004">            } catch (IOException ioe) {</span><a href="#l1004"></a>
<span id="l1005">                // should not happen</span><a href="#l1005"></a>
<span id="l1006">                throw new AssertionError(ioe);</span><a href="#l1006"></a>
<span id="l1007">            }</span><a href="#l1007"></a>
<span id="l1008">        }</span><a href="#l1008"></a>
<span id="l1009">    }</span><a href="#l1009"></a>
<span id="l1010"></span><a href="#l1010"></a>
<span id="l1011">    protected void implCloseSelectableChannel() throws IOException {</span><a href="#l1011"></a>
<span id="l1012">        synchronized (stateLock) {</span><a href="#l1012"></a>
<span id="l1013">            if (state != ST_KILLED)</span><a href="#l1013"></a>
<span id="l1014">                nd.preClose(fd);</span><a href="#l1014"></a>
<span id="l1015">            ResourceManager.afterUdpClose();</span><a href="#l1015"></a>
<span id="l1016"></span><a href="#l1016"></a>
<span id="l1017">            // if member of mulitcast group then invalidate all keys</span><a href="#l1017"></a>
<span id="l1018">            if (registry != null)</span><a href="#l1018"></a>
<span id="l1019">                registry.invalidateAll();</span><a href="#l1019"></a>
<span id="l1020"></span><a href="#l1020"></a>
<span id="l1021">            long th;</span><a href="#l1021"></a>
<span id="l1022">            if ((th = readerThread) != 0)</span><a href="#l1022"></a>
<span id="l1023">                NativeThread.signal(th);</span><a href="#l1023"></a>
<span id="l1024">            if ((th = writerThread) != 0)</span><a href="#l1024"></a>
<span id="l1025">                NativeThread.signal(th);</span><a href="#l1025"></a>
<span id="l1026">            if (!isRegistered())</span><a href="#l1026"></a>
<span id="l1027">                kill();</span><a href="#l1027"></a>
<span id="l1028">        }</span><a href="#l1028"></a>
<span id="l1029">    }</span><a href="#l1029"></a>
<span id="l1030"></span><a href="#l1030"></a>
<span id="l1031">    public void kill() throws IOException {</span><a href="#l1031"></a>
<span id="l1032">        synchronized (stateLock) {</span><a href="#l1032"></a>
<span id="l1033">            if (state == ST_KILLED)</span><a href="#l1033"></a>
<span id="l1034">                return;</span><a href="#l1034"></a>
<span id="l1035">            if (state == ST_UNINITIALIZED) {</span><a href="#l1035"></a>
<span id="l1036">                state = ST_KILLED;</span><a href="#l1036"></a>
<span id="l1037">                return;</span><a href="#l1037"></a>
<span id="l1038">            }</span><a href="#l1038"></a>
<span id="l1039">            assert !isOpen() &amp;&amp; !isRegistered();</span><a href="#l1039"></a>
<span id="l1040">            nd.close(fd);</span><a href="#l1040"></a>
<span id="l1041">            state = ST_KILLED;</span><a href="#l1041"></a>
<span id="l1042">        }</span><a href="#l1042"></a>
<span id="l1043">    }</span><a href="#l1043"></a>
<span id="l1044"></span><a href="#l1044"></a>
<span id="l1045">    protected void finalize() throws IOException {</span><a href="#l1045"></a>
<span id="l1046">        // fd is null if constructor threw exception</span><a href="#l1046"></a>
<span id="l1047">        if (fd != null)</span><a href="#l1047"></a>
<span id="l1048">            close();</span><a href="#l1048"></a>
<span id="l1049">    }</span><a href="#l1049"></a>
<span id="l1050"></span><a href="#l1050"></a>
<span id="l1051">    /**</span><a href="#l1051"></a>
<span id="l1052">     * Translates native poll revent set into a ready operation set</span><a href="#l1052"></a>
<span id="l1053">     */</span><a href="#l1053"></a>
<span id="l1054">    public boolean translateReadyOps(int ops, int initialOps,</span><a href="#l1054"></a>
<span id="l1055">                                     SelectionKeyImpl sk) {</span><a href="#l1055"></a>
<span id="l1056">        int intOps = sk.nioInterestOps(); // Do this just once, it synchronizes</span><a href="#l1056"></a>
<span id="l1057">        int oldOps = sk.nioReadyOps();</span><a href="#l1057"></a>
<span id="l1058">        int newOps = initialOps;</span><a href="#l1058"></a>
<span id="l1059"></span><a href="#l1059"></a>
<span id="l1060">        if ((ops &amp; Net.POLLNVAL) != 0) {</span><a href="#l1060"></a>
<span id="l1061">            // This should only happen if this channel is pre-closed while a</span><a href="#l1061"></a>
<span id="l1062">            // selection operation is in progress</span><a href="#l1062"></a>
<span id="l1063">            // ## Throw an error if this channel has not been pre-closed</span><a href="#l1063"></a>
<span id="l1064">            return false;</span><a href="#l1064"></a>
<span id="l1065">        }</span><a href="#l1065"></a>
<span id="l1066"></span><a href="#l1066"></a>
<span id="l1067">        if ((ops &amp; (Net.POLLERR | Net.POLLHUP)) != 0) {</span><a href="#l1067"></a>
<span id="l1068">            newOps = intOps;</span><a href="#l1068"></a>
<span id="l1069">            sk.nioReadyOps(newOps);</span><a href="#l1069"></a>
<span id="l1070">            return (newOps &amp; ~oldOps) != 0;</span><a href="#l1070"></a>
<span id="l1071">        }</span><a href="#l1071"></a>
<span id="l1072"></span><a href="#l1072"></a>
<span id="l1073">        if (((ops &amp; Net.POLLIN) != 0) &amp;&amp;</span><a href="#l1073"></a>
<span id="l1074">            ((intOps &amp; SelectionKey.OP_READ) != 0))</span><a href="#l1074"></a>
<span id="l1075">            newOps |= SelectionKey.OP_READ;</span><a href="#l1075"></a>
<span id="l1076"></span><a href="#l1076"></a>
<span id="l1077">        if (((ops &amp; Net.POLLOUT) != 0) &amp;&amp;</span><a href="#l1077"></a>
<span id="l1078">            ((intOps &amp; SelectionKey.OP_WRITE) != 0))</span><a href="#l1078"></a>
<span id="l1079">            newOps |= SelectionKey.OP_WRITE;</span><a href="#l1079"></a>
<span id="l1080"></span><a href="#l1080"></a>
<span id="l1081">        sk.nioReadyOps(newOps);</span><a href="#l1081"></a>
<span id="l1082">        return (newOps &amp; ~oldOps) != 0;</span><a href="#l1082"></a>
<span id="l1083">    }</span><a href="#l1083"></a>
<span id="l1084"></span><a href="#l1084"></a>
<span id="l1085">    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l1085"></a>
<span id="l1086">        return translateReadyOps(ops, sk.nioReadyOps(), sk);</span><a href="#l1086"></a>
<span id="l1087">    }</span><a href="#l1087"></a>
<span id="l1088"></span><a href="#l1088"></a>
<span id="l1089">    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {</span><a href="#l1089"></a>
<span id="l1090">        return translateReadyOps(ops, 0, sk);</span><a href="#l1090"></a>
<span id="l1091">    }</span><a href="#l1091"></a>
<span id="l1092"></span><a href="#l1092"></a>
<span id="l1093">    // package-private</span><a href="#l1093"></a>
<span id="l1094">    int poll(int events, long timeout) throws IOException {</span><a href="#l1094"></a>
<span id="l1095">        assert Thread.holdsLock(blockingLock()) &amp;&amp; !isBlocking();</span><a href="#l1095"></a>
<span id="l1096"></span><a href="#l1096"></a>
<span id="l1097">        synchronized (readLock) {</span><a href="#l1097"></a>
<span id="l1098">            int n = 0;</span><a href="#l1098"></a>
<span id="l1099">            try {</span><a href="#l1099"></a>
<span id="l1100">                begin();</span><a href="#l1100"></a>
<span id="l1101">                synchronized (stateLock) {</span><a href="#l1101"></a>
<span id="l1102">                    if (!isOpen())</span><a href="#l1102"></a>
<span id="l1103">                        return 0;</span><a href="#l1103"></a>
<span id="l1104">                    readerThread = NativeThread.current();</span><a href="#l1104"></a>
<span id="l1105">                }</span><a href="#l1105"></a>
<span id="l1106">                n = Net.poll(fd, events, timeout);</span><a href="#l1106"></a>
<span id="l1107">            } finally {</span><a href="#l1107"></a>
<span id="l1108">                readerThread = 0;</span><a href="#l1108"></a>
<span id="l1109">                end(n &gt; 0);</span><a href="#l1109"></a>
<span id="l1110">            }</span><a href="#l1110"></a>
<span id="l1111">            return n;</span><a href="#l1111"></a>
<span id="l1112">        }</span><a href="#l1112"></a>
<span id="l1113">    }</span><a href="#l1113"></a>
<span id="l1114"></span><a href="#l1114"></a>
<span id="l1115">    /**</span><a href="#l1115"></a>
<span id="l1116">     * Translates an interest operation set into a native poll event set</span><a href="#l1116"></a>
<span id="l1117">     */</span><a href="#l1117"></a>
<span id="l1118">    public void translateAndSetInterestOps(int ops, SelectionKeyImpl sk) {</span><a href="#l1118"></a>
<span id="l1119">        int newOps = 0;</span><a href="#l1119"></a>
<span id="l1120"></span><a href="#l1120"></a>
<span id="l1121">        if ((ops &amp; SelectionKey.OP_READ) != 0)</span><a href="#l1121"></a>
<span id="l1122">            newOps |= Net.POLLIN;</span><a href="#l1122"></a>
<span id="l1123">        if ((ops &amp; SelectionKey.OP_WRITE) != 0)</span><a href="#l1123"></a>
<span id="l1124">            newOps |= Net.POLLOUT;</span><a href="#l1124"></a>
<span id="l1125">        if ((ops &amp; SelectionKey.OP_CONNECT) != 0)</span><a href="#l1125"></a>
<span id="l1126">            newOps |= Net.POLLIN;</span><a href="#l1126"></a>
<span id="l1127">        sk.selector.putEventOps(sk, newOps);</span><a href="#l1127"></a>
<span id="l1128">    }</span><a href="#l1128"></a>
<span id="l1129"></span><a href="#l1129"></a>
<span id="l1130">    public FileDescriptor getFD() {</span><a href="#l1130"></a>
<span id="l1131">        return fd;</span><a href="#l1131"></a>
<span id="l1132">    }</span><a href="#l1132"></a>
<span id="l1133"></span><a href="#l1133"></a>
<span id="l1134">    public int getFDVal() {</span><a href="#l1134"></a>
<span id="l1135">        return fdVal;</span><a href="#l1135"></a>
<span id="l1136">    }</span><a href="#l1136"></a>
<span id="l1137"></span><a href="#l1137"></a>
<span id="l1138"></span><a href="#l1138"></a>
<span id="l1139">    // -- Native methods --</span><a href="#l1139"></a>
<span id="l1140"></span><a href="#l1140"></a>
<span id="l1141">    private static native void initIDs();</span><a href="#l1141"></a>
<span id="l1142"></span><a href="#l1142"></a>
<span id="l1143">    private static native void disconnect0(FileDescriptor fd, boolean isIPv6)</span><a href="#l1143"></a>
<span id="l1144">        throws IOException;</span><a href="#l1144"></a>
<span id="l1145"></span><a href="#l1145"></a>
<span id="l1146">    private native int receive0(FileDescriptor fd, long address, int len,</span><a href="#l1146"></a>
<span id="l1147">                                boolean connected)</span><a href="#l1147"></a>
<span id="l1148">        throws IOException;</span><a href="#l1148"></a>
<span id="l1149"></span><a href="#l1149"></a>
<span id="l1150">    private native int send0(boolean preferIPv6, FileDescriptor fd, long address,</span><a href="#l1150"></a>
<span id="l1151">                             int len, InetAddress addr, int port)</span><a href="#l1151"></a>
<span id="l1152">        throws IOException;</span><a href="#l1152"></a>
<span id="l1153"></span><a href="#l1153"></a>
<span id="l1154">    static {</span><a href="#l1154"></a>
<span id="l1155">        IOUtil.load();</span><a href="#l1155"></a>
<span id="l1156">        initIDs();</span><a href="#l1156"></a>
<span id="l1157">    }</span><a href="#l1157"></a>
<span id="l1158"></span><a href="#l1158"></a>
<span id="l1159">}</span><a href="#l1159"></a></pre>
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

