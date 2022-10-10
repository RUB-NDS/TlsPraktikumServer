package de.rub.nds.praktikum.crypto;

import de.rub.nds.praktikum.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe3.class)
public class HkdFunctionTest {

    /**
     *
     */
    @Test
    public void testExtractHandshake() {
        byte[] expand = HkdFunction.extract(Util.hexStringToByteArray("6f2615a108c702c5678f54fc9dbab69716c076189c48250cebeac3576c3611ba"), Util.hexStringToByteArray("8151d1464c1b55533623b9c2246a6a0e6e7e185063e14afdaff0b6e1c61a8642"));
        assertArrayEquals(Util.hexStringToByteArray("5b4f965df03c682c46e6ee86c311636615a1d2bbb24345c25205953c879e8d06"), expand);
    }

    /**
     *
     */
    @Test
    public void testExtractEarly() {
        byte[] expand = HkdFunction.extract(Util.hexStringToByteArray(""), Util.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
        assertArrayEquals(Util.hexStringToByteArray("33ad0a1c607ec03b09e6cd9893680ce210adf300aa1f2660e1b22e10f170f92a"), expand);
    }

    /**
     *
     */
    @Test
    public void testExtractMaster() {
        byte[] expand = HkdFunction.extract(Util.hexStringToByteArray("c8615719e2403747b610762c72b8f4da5c60995765d404a9d006b9b0727ba583"), Util.hexStringToByteArray("0000000000000000000000000000000000000000000000000000000000000000"));
        assertArrayEquals(Util.hexStringToByteArray("5c79d169424e262b563203627be4eb51033f588c43c9ce0373372dbcbc0185a7"), expand);
    }

    /**
     *
     */
    @Test
    public void testExpand() {
        byte[] expand = HkdFunction.expand(Util.hexStringToByteArray("3b7a839c239ef2bf0b7305a0e0c4e5a8c6c69330a753b308f5e3a83aa2ef6979"), Util.hexStringToByteArray("001009746c733133206b657900"), 16);
        assertArrayEquals(Util.hexStringToByteArray("c66cb1aec519df44c91e10995511ac8b"), expand);
    }

    /**
     *
     */
    @Test
    public void testExpandLong() throws NoSuchAlgorithmException {
        byte[] expand = HkdFunction.expand(Util.hexStringToByteArray("3b7a839c239ef2bf0b7305a0e0c4e5a8c6c69330a753b308f5e3a83aa2ef6979"), Util.hexStringToByteArray("001009746c733133206b657900"), 1024);
        assertArrayEquals(Util.hexStringToByteArray("c66cb1aec519df44c91e10995511ac8b5c5a2fd3e4230abfe61c8c9ad6c687efecc7887b615e3cede161b2137417cb0117bb166488cffa72d0a0fb4c9853fca81d4280eb43fa3abd583e4629c77755cb0b30d1684897d0d4e6de2142f0657f8cf6ef24779456dc16e2beb00f6d4ac888cb2069f7515348c5b8662221b7195687aacec75f03d4d8f91599e1a9565f78472e8630faac6323f0b5abb73292d64aa8c7769f0fe4cf61d60f4bc0beaf00642be4c25ca84c37546faeea79d50fd936940de8653d97ecc01737df3202a561efa27402243c47462f9b85e13f65c40676c4d17194fb642790e947ebbf945b3a6f4ca27e0c614d3b417f756c88f396ae45dad478d130bbe984ce45d8549dbb30a2d5a19f9bd11e5825776c8b6cd0d791876166e14b88fbb91abb2c6e2070ae97c7952a17663884c858e53df223b240aaffdf41a585c2e5d5afef7e474d152b1c55f68527f6c23021826b44a14cfc7f00a6ce4452bd1b5f0a25d0572db516ea31b5ac4ed96d0946889c5bfb87aba19b9955e8039a1417951fbae521dcc3e10a691a153ed9602fc501454e2b8edf6bfc121b169d3797eef366ec3aa1fb5b315412b608f04144feea14125f2a15a2e38ede47acadca7fe4d8932607abc094eaf1df080cb1295f4645fff54f5fbdc39bdd64b60e72b7b5849ba72bac0adbcd914a1bc6cd081e9f82399abb356100a1966602f7371ec32867eee2670cffef4804df886f5d899aebde65afe0e748b93a54c5096a22b52e287829e6935722c0b8520ac748c0fcf917698ce0b8b53e958625d9914735604d179d4aee20281a5b140bf5828abe7ef02b917a783865f43cd996a7446a769552a42d6dfa9599d5bbac054ea7f1b9d2d175de1b2753eda333071845377c3cb08e1c4aba7a1c6ccd3a1fb071261ee05fa787634190b40e948559d42d28a2bd07b9b75c27923f261e40222d002ba8032d71b10f4987ee8bd0338691658ea65c830872e9015550841d6f94ebd740a83384f73c35f14d8669919dbedfab3e060c6be8462f76a2b110a84bc20394b943974e271a2be56b12e5b0095130d99037d8a4df5788ee5f0e48acb4e61c94a9b689ebe414ef903c71a14b93fa0d99878182f083d928cfc0cfc9061805118eb370bf02cc12f2d814e5b80755a7bc3a4d2ccd50b44c92285c33680149571af77dfc7361882ce7d45d4d289dcbd11e89b66124de9de6bdea5f13df7d84b9d5937f78541820ecd0eb3024dfbe25cd7761c4d15736f89e6d87b7da840e3f88c63d5d2041990d46f7bd7497395ec8446cb6358a678fcc07c7dc36ae2a64c1105084ba99b0bf16235ab3871d37561d29a3a296e0a493a92dcab5a416ab0c1b203425c7717f52514e29a2bba4c4a95a2f822e9c849cc88e26a7676a8f292542e5b37d825ae4b1be14970146de53f8d644efca55fe79"), expand);
        }

    /**
     *
     */
    @Test
    public void testDeriveSecret() {
        byte[] derivedSecret = HkdFunction.deriveSecret(Util.hexStringToByteArray("33ad0a1c607ec03b09e6cd9893680ce210adf300aa1f2660e1b22e10f170f92a"), "derived", Util.hexStringToByteArray(""));
        assertArrayEquals(Util.hexStringToByteArray("6f2615a108c702c5678f54fc9dbab69716c076189c48250cebeac3576c3611ba"), derivedSecret);
    }

    /**
     *
     */
    @Test
    public void testExpandLabel() {
        byte[] expandedLabel = HkdFunction.expandLabel(Util.hexStringToByteArray("33ad0a1c607ec03b09e6cd9893680ce210adf300aa1f2660e1b22e10f170f92a"), "derived", Util.hexStringToByteArray(""), 20);
        assertArrayEquals(Util.hexStringToByteArray("725e98976eae3c4ea5687c9253f19b1f78f2a73e"), expandedLabel);
    }

}
