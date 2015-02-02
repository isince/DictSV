package nd.dictsv.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Since on 17/1/2558.
 */
public class WordDB {

    public static final String TAG = "WordDB";

    private Context mContext;

    //Database field
    private DBHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    //data
    private String computer_Category = "Computer";
    private String math_Category = "Math";
    private ArrayList<String> mCategory = new ArrayList<>();

    public WordDB() {
    }

    public WordDB(Context context) {
        this.mContext = context;


    }

    public void createCategory() {
        mCategory.add("Category");
        mCategory.add(1, computer_Category);
        mCategory.add(2, math_Category);

        //Category
        CategoryDAO categoryDAO = new CategoryDAO(mContext);
        categoryDAO.addCategory(mCategory.get(getIndexByName(computer_Category)));
        categoryDAO.addCategory(mCategory.get(getIndexByName(math_Category)));


    }

    public void createWord() {

        //Word
        WordDAO wordDAO = new WordDAO(mContext);
        // ศัพท์ ศัพท์บัญญัติ คำทับศัพท์
        // word terminology transliterated
        //Array list start at 0

        wordDAO.addWord("A", "เอ", null, getIndexByName(computer_Category));
        wordDAO.addWord("Air", "แอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("Asia", "เอเซีย", null, getIndexByName(computer_Category));
        wordDAO.addWord("B", "บี", null, getIndexByName(computer_Category));
        wordDAO.addWord("C", "ซี", null, getIndexByName(computer_Category));
        wordDAO.addWord("Computer", "คอมพิวเตอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("E", "อี", null, getIndexByName(math_Category));
        wordDAO.addWord("F", "เอฟ", null, getIndexByName(math_Category));
        wordDAO.addWord("G", "จี", null, getIndexByName(computer_Category));
        wordDAO.addWord("H", "เฮซ", null, getIndexByName(computer_Category));
        wordDAO.addWord("abend; abnormal end", "การหยุดงานผิดปรกติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("abort", "ยกเลิก", null, getIndexByName(computer_Category));
        wordDAO.addWord("absolute address; machine address", "เลขที่อยู่สัมบูรณ์", null, getIndexByName(computer_Category));
        wordDAO.addWord("absolute code", "รหัสสัมบูรณ์", null, getIndexByName(computer_Category));
        wordDAO.addWord("absolute loader", "โปรแกรมบรรจุสัมบูรณ์", null, getIndexByName(computer_Category));
        wordDAO.addWord("abstract data type (ADT)", "แบบชนิดข้อมูลนามธรรม", null, getIndexByName(computer_Category));
        wordDAO.addWord("abstract machine", "เครื่องเชิงนามธรรม", null, getIndexByName(computer_Category));
        wordDAO.addWord("abstraction", "การกำหนดสาระสำคัญ", null, getIndexByName(computer_Category));
        wordDAO.addWord("acceptance test", "การทดสอบเพื่อยอมรับ", null, getIndexByName(computer_Category));
        wordDAO.addWord("access", "๑. เข้าถึง\n" + "๒. การเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access arm", "ก้านเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access code", "รหัสการเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access denied", "ข้อความห้ามเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access key", "กุญแจการเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access level", "ระดับการเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access mechanism", "กลไกการเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access method", "๑. วิธีเข้าถึง\n" + "๒. โปรแกรมสำหรับเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("access time", "ช่วงเวลาเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("accessor", "ตัวเข้าถึง", null, getIndexByName(computer_Category));
        wordDAO.addWord("account", "บัญชีผู้ใช้", null, getIndexByName(computer_Category));
        wordDAO.addWord("accounting machine", "เครื่องทำบัญชี", null, getIndexByName(computer_Category));
        wordDAO.addWord("accumulator", "ตัวสะสม", null, getIndexByName(computer_Category));
        wordDAO.addWord("accuracy", "ความแม่น", null, getIndexByName(computer_Category));
        wordDAO.addWord("acknowledgement", "การตอบรับ", null, getIndexByName(computer_Category));
        wordDAO.addWord("acoustic coupler", "ตัวคู่ต่อทางเสียง", null, getIndexByName(computer_Category));
        wordDAO.addWord("acoustic delay line", "สายหน่วงเสียง", null, getIndexByName(computer_Category));
        wordDAO.addWord("acoustic memory; acoustic store", "หน่วยความจำเชิงเสียง", null, getIndexByName(computer_Category));
        wordDAO.addWord("acronym", "รัสพจน์", null, getIndexByName(computer_Category));
        wordDAO.addWord("active device", "อุปกรณ์กัมมันต์, อุปกรณ์แอ็กทิฟ", null, getIndexByName(computer_Category));
        wordDAO.addWord("activity diagram", "แผนภาพกิจกรรม", null, getIndexByName(computer_Category));
        wordDAO.addWord("activity ratio", "อัตราส่วนกิจกรรม", null, getIndexByName(computer_Category));
        wordDAO.addWord("actual code", "รหัสจริง", null, getIndexByName(computer_Category));
        wordDAO.addWord("actual decimal point", "จุดทศนิยมจริง", null, getIndexByName(computer_Category));
        wordDAO.addWord("actual instruction", "คำสั่งจริง", null, getIndexByName(computer_Category));
        wordDAO.addWord("acyclic graph", "กราฟไม่มีวง", null, getIndexByName(computer_Category));
        wordDAO.addWord("Ada", "(ภาษา)เอดา", "เอดา", getIndexByName(computer_Category)); //TODo have translate
        wordDAO.addWord("adapter", "ตัวปรับต่อ, ตัวปรับ", null, getIndexByName(computer_Category));
        wordDAO.addWord("addend", "ตัวบวก", null, getIndexByName(computer_Category));
        wordDAO.addWord("adder", "๑. เครื่องบวก\n" + "๒. วงจรบวก", null, getIndexByName(computer_Category));
        wordDAO.addWord("adder-subtracter", "วงจรบวกลบ", null, getIndexByName(computer_Category));
        wordDAO.addWord("addition", "การบวก", null, getIndexByName(computer_Category));
        wordDAO.addWord("additional character", "อักขระเพิ่มเติม", null, getIndexByName(computer_Category));
        wordDAO.addWord("address", "เลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address computation", "การคำนวณเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address format", "รูปแบบเลขที่อยู่ [ในคำสั่ง]", null, getIndexByName(computer_Category));
        wordDAO.addWord("address mapping", "การส่งเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address modification", "การดัดแปรเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address part", "ส่วนเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address register", "เรจิสเตอร์เลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("address track", "๑. วงเก็บเลขที่อยู่\n" + "๒. ร่องเก็บเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("addressing", "การกำหนดเลขที่อยู่", null, getIndexByName(computer_Category));
        wordDAO.addWord("advanced program to program communications (APPC)", "การสื่อสารระดับสูงระหว่างโปรแกรม (เอพีพีซี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("Advanced Research Projects Agency Network (ARPANET)", "เครือข่ายสำนักงานโครงการวิจัยขั้นสูง (อาร์พาเน็ต)", null, getIndexByName(computer_Category));
        wordDAO.addWord("advanced SCSI program interface (ASPI)", "ส่วนต่อประสานโปรแกรมสกัสซีระดับสูง (เอเอสพีไอ)", null, getIndexByName(computer_Category));
        wordDAO.addWord("aggregation", "ภาพรวมกลุ่ม", null, getIndexByName(computer_Category));
        wordDAO.addWord("algorithm", "ขั้นตอนวิธี", null, getIndexByName(computer_Category));//TODO งงว่ามีในทับศัพท์หรือไม่
        wordDAO.addWord("algorithmic complexity", "ความซับซ้อนของขั้นตอนวิธี", null, getIndexByName(computer_Category));
        wordDAO.addWord("algorithmic language", "ภาษาขั้นตอนวิธี", null, getIndexByName(computer_Category));
        wordDAO.addWord("alias", "สมนาม [อ่านว่า สะมะนาม]", null, getIndexByName(computer_Category));
        wordDAO.addWord("allocate", "จัดสรร", null, getIndexByName(computer_Category));
        wordDAO.addWord("all-purpose computer; general-purpose computer", "คอมพิวเตอร์อเนกประสงค์", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphabet", "ชุดตัวอักษร", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphabetic", "ตัวอักษร", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphabetic code", "รหัสตัวอักษร", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphabetic string", "สายตัวอักษร", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphameric; alphanumeric", "ตัวอักษรเลข", null, getIndexByName(computer_Category));
        wordDAO.addWord("alphanumeric mode", "ภาวะตัวอักษรเลข [= character mode และ text mode]", null, getIndexByName(computer_Category));
        wordDAO.addWord("alternate key", "กุญแจสำรอง [ใช้กับแฟ้ม]", null, getIndexByName(computer_Category));
        wordDAO.addWord("Alternate key; Alt key", "แป้นสลับ", null, getIndexByName(computer_Category));
        wordDAO.addWord("alternate track", "๑. วงสำรอง\n" + "๒. ร่องสำรอง", null, getIndexByName(computer_Category));
        wordDAO.addWord("alternative denial", "การปฏิเสธแบบเลือก", null, getIndexByName(computer_Category));
        wordDAO.addWord("ambiguity error", "ความผิดพลาดกำกวม", null, getIndexByName(computer_Category));
        wordDAO.addWord("amendment file", "แฟ้มแก้ไข", null, getIndexByName(computer_Category));
        wordDAO.addWord("amendment record", "ระเบียนแก้ไข", null, getIndexByName(computer_Category));
        wordDAO.addWord("amendment tape", "แถบแก้ไข", null, getIndexByName(computer_Category));
        wordDAO.addWord("American National Standard Institute (ANSI)", "สถาบันมาตรฐานแห่งชาติของสหรัฐอเมริกา (แอนซี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("American Standard Code for Information Interchange (ASCII)", "รหัสมาตรฐานของสหรัฐอเมริกาเพื่อการสับเปลี่ยนสารสนเทศ (แอสกี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("analog; analogue", "เชิงอุปมาน,แอนะล็อก", "แอนะล็อก", getIndexByName(computer_Category));//TODO have
        wordDAO.addWord("analog computer", "คอมพิวเตอร์เชิงอุปมาน", "แอนะล็อกคอมพิวเตอร์", getIndexByName(computer_Category));//TODO have
        wordDAO.addWord("analog data", "ข้อมูลเชิงอุปมาน, ข้อมูลแอนะล็อก", null, getIndexByName(computer_Category));
        wordDAO.addWord("analog network", "วงจรข่ายเชิงอุปมาน, วงจรข่ายแอนะล็อก", null, getIndexByName(computer_Category));
        wordDAO.addWord("analog signal", "สัญญาณเชิงอุปมาน, สัญญาณแอนะล็อก", null, getIndexByName(computer_Category));
        wordDAO.addWord("analog-to-digital converter (ADC)", "ตัวแปลงแอนะล็อกเป็นดิจิทัล, ตัวเปลี่ยนแอนะล็อกเป็นดิจิทัล (เอดีซี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("analogue; analog", "เชิงอุปมาน, แอนะล็อก", "แอนะล็อก", getIndexByName(computer_Category));
        wordDAO.addWord("analyser; analyzer", "๑. ตัววิเคราะห์, เครื่องวิเคราะห์\n" + "๒. โปรแกรมวิเคราะห์", null, getIndexByName(computer_Category));
        wordDAO.addWord("ancestor node", "บัพบน", null, getIndexByName(computer_Category));
        wordDAO.addWord("animation", "๑. การทำให้เคลื่อนไหว\n" + "๒. การทำภาพเคลื่อนไหว\n" + "๓. ภาพเคลื่อนไหว", null, getIndexByName(computer_Category));
        wordDAO.addWord("anonymous FTP", "เอฟทีพีแบบนิรนาม", null, getIndexByName(computer_Category));
        wordDAO.addWord("append", "ผนวก", null, getIndexByName(computer_Category));
        wordDAO.addWord("applet", "แอปเพล็ต", "แอปเพล็ต", getIndexByName(computer_Category));//TODO have
        wordDAO.addWord("application", "๑. การประยุกต์\n" + "๒. โปรแกรมประยุกต์\n" + "๓. ระบบประยุกต์\n" + "๔. งานประยุกต์", null, getIndexByName(computer_Category));//TODO
        wordDAO.addWord("application package", "โปรแกรมสำเร็จประยุกต์", null, getIndexByName(computer_Category));
        wordDAO.addWord("application program interface (API)", "ส่วนต่อประสานโปรแกรมประยุกต์ (เอพีไอ)", null, getIndexByName(computer_Category));
        wordDAO.addWord("application specific integrated circuit (ASIC)", "วงจรรวมเฉพาะงาน (เอสิก)", null, getIndexByName(computer_Category));
        wordDAO.addWord("applicative language; functional language", "ภาษาเชิงหน้าที่", null, getIndexByName(computer_Category));
        wordDAO.addWord("architecture; computer architecture", "สถาปัตยกรรมคอมพิวเตอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("archive", "หน่วยเก็บถาวร", null, getIndexByName(computer_Category));
        wordDAO.addWord("archived file", "แฟ้มเก็บถาวร", null, getIndexByName(computer_Category));
        wordDAO.addWord("archiving", "การเก็บถาวร", null, getIndexByName(computer_Category));
        wordDAO.addWord("argument", "อาร์กิวเมนต์", "อาร์กิวเมนต์", getIndexByName(computer_Category));//TODO have
        wordDAO.addWord("arithmetic expression", "นิพจน์คำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic instruction", "คำสั่งคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic operation", "การดำเนินการคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic operator", "ตัวดำเนินการคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic register", "เรจิสเตอร์คำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic relation", "ความสัมพันธ์เชิงคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic shift; arithmetical shift", "การเลื่อนเชิงคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic statement", "ข้อความสั่งคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic unit", "หน่วยคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("arithmetic-logic unit (ALU)", "หน่วยคำนวณและตรรกะ (เอแอลยู)", null, getIndexByName(computer_Category));//TODO
        wordDAO.addWord("arithmetical shift; arithmetic shift", "การเลื่อนเชิงคำนวณ", null, getIndexByName(computer_Category));
        wordDAO.addWord("array", "แถวลำดับ", null, getIndexByName(computer_Category));//TODO
        wordDAO.addWord("arrow key", "แป้นลูกศร", null, getIndexByName(computer_Category));
        wordDAO.addWord("artificial intelligence (AI)", "ปัญญาประดิษฐ์ (เอไอ)", null, getIndexByName(computer_Category));
        wordDAO.addWord("ascending sort", "การเรียงลำดับขึ้น", null, getIndexByName(computer_Category));
        wordDAO.addWord("assemble", "แปลภาษาแอสเซมบลี", null, getIndexByName(computer_Category));
        wordDAO.addWord("assembler", "แอสเซมเบลอร์", "แอสเซมเบลอร์", getIndexByName(computer_Category));//TODO have
        wordDAO.addWord("assembly language", "ภาษาแอสเซมบลี", null, getIndexByName(computer_Category));
        wordDAO.addWord("assembly program", "โปรแกรมภาษาแอสเซมบลี", null, getIndexByName(computer_Category));
        wordDAO.addWord("assertion", "ข้อความยืนยัน", null, getIndexByName(computer_Category));
        wordDAO.addWord("assign", "กำหนด", null, getIndexByName(computer_Category));
        wordDAO.addWord("assignment operator", "ตัวกำหนดค่า", null, getIndexByName(computer_Category));
        wordDAO.addWord("assignment statement", "ข้อความสั่งกำหนดค่า", null, getIndexByName(computer_Category));
        wordDAO.addWord("associative memory", "หน่วยความจำสาระ", null, getIndexByName(computer_Category));
        wordDAO.addWord("associative storage", "หน่วยเก็บสาระ", null, getIndexByName(computer_Category));
        wordDAO.addWord("assumed decimal point", "จุดทศนิยมสมมุติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("asymmetric digital subscriber line (ADSL)", "สายผู้เช่าดิจิทัลแบบอสมมาตร (เอดีเอสแอล)", null, getIndexByName(computer_Category));
        wordDAO.addWord("asynchronous", "ไม่ประสานเวลา, อสมวาร, อะซิงโครนัส", "อะซิงโครนัส", getIndexByName(computer_Category));//TODO
        wordDAO.addWord("asynchronous computer", "คอมพิวเตอร์แบบไม่ประสานเวลา", null, getIndexByName(computer_Category));
        wordDAO.addWord("asynchronous device", "อุปกรณ์แบบไม่ประสานเวลา", null, getIndexByName(computer_Category));
        wordDAO.addWord("asynchronous port", "ช่องทางไม่ประสานเวลา", null, getIndexByName(computer_Category));
        wordDAO.addWord("asynchronous transfer mode (ATM)", "ภาวะถ่ายโอนแบบไม่ประสานเวลา (เอทีเอ็ม)", null, getIndexByName(computer_Category));
        wordDAO.addWord("asynchronous transmission", "การส่งแบบไม่ประสานเวลา", null, getIndexByName(computer_Category));
        wordDAO.addWord("ATM adaptation layer (AAL)", "ชั้นการปรับเปลี่ยนข้อมูลเอทีเอ็ม (เอเอแอล)", null, getIndexByName(computer_Category));
        wordDAO.addWord("atomicity", "ภาวะครบหน่วย", null, getIndexByName(computer_Category));
        wordDAO.addWord("attribute", "ลักษณะประจำ", null, getIndexByName(computer_Category));//TODO
        wordDAO.addWord("attribute grammar", "ไวยากรณ์เชิงลักษณะประจำ", null, getIndexByName(computer_Category));
        wordDAO.addWord("audio response unit", "หน่วยตอบสนองด้วยเสียง", null, getIndexByName(computer_Category));
        wordDAO.addWord("audit of computer systems", "การตรวจสอบระบบคอมพิวเตอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("audit trail", "หลักฐานการตรวจสอบ", null, getIndexByName(computer_Category));
        wordDAO.addWord("augend", "ตัวตั้งบวก", null, getIndexByName(computer_Category));
        wordDAO.addWord("augment", "แต่งเติม, เสริม", null, getIndexByName(computer_Category));
        wordDAO.addWord("augmented reality", "ความเป็นจริงเสริม", null, getIndexByName(computer_Category));
        wordDAO.addWord("augmenter", "ตัวแต่งเติม", null, getIndexByName(computer_Category));
        wordDAO.addWord("authentication", "การพิสูจน์ตัวจริง", null, getIndexByName(computer_Category));
        wordDAO.addWord("authoring language", "ภาษาสร้างโปรแกรมบทเรียน", null, getIndexByName(computer_Category));
        wordDAO.addWord("auto-answer", "รับ(โทรศัพท์)อัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("auto-call", "เรียกอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("auto-dial", "ต่อเลขหมายอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("auto-dialing modem", "โมเด็มต่อเลขหมายอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("auto-restart", "เริ่มทำต่ออัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automata theory", "ทฤษฎีออโตมาตา", null, getIndexByName(computer_Category));
        wordDAO.addWord("automate", "ทำให้เป็นอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automated office", "สำนักงานอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automated theorem proving", "การพิสูจน์ทฤษฎีบทด้วยเครื่อง", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic abstract", "บทคัดย่ออัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic calling unit (ACU)", "หน่วยเรียกอัตโนมัติ (เอซียู)", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic carriage return", "การขึ้นบรรทัดใหม่อัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic check", "การตรวจสอบอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic coding", "การเข้ารหัสอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic data capture", "การยึดเก็บข้อมูลอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic data processing (ADP)", "การประมวลผลข้อมูลแบบอัตโนมัติ (เอดีพี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic decimal adjustment", "การปรับทศนิยมอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic dictionary", "พจนานุกรมอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic error correction", "การแก้ความผิดพลาดโดยอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic error detection", "การตรวจหาความผิดพลาดโดยอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic exchange", "ชุมสายอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic feed-punch; automatic punch", "เครื่องเจาะบัตรอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic interrupt", "การขัดจังหวะอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic loader", "โปรแกรมบรรจุอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic programming", "การสร้างโปรแกรมอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic punch; automatic feed-punch", "เครื่องเจาะบัตรอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic repeat", "การซ้ำอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic send-receive set (ASR)", "ชุดรับส่งอัตโนมัติ (เอเอสอาร์)", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic sequencing", "การจัดลำดับอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic stop", "การหยุดอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic tape punch", "เครื่องเจาะแถบกระดาษอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic teller machine (ATM)", "เครื่องรับจ่ายเงินอัตโนมัติ (เอทีเอ็ม)", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic test equipment (ATE)", "เครื่องทดสอบอัตโนมัติ (เอทีอี)", null, getIndexByName(computer_Category));
        wordDAO.addWord("automatic verifier", "เครื่องทวนสอบอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automation", "การทำให้เป็นอัตโนมัติ, การอัตโนมัติ, การวางระบบอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("automonitor", "โปรแกรมเฝ้าสังเกตตัวเอง", null, getIndexByName(computer_Category));
        wordDAO.addWord("autonomy", "ภาวะอิสระ, ภาวะอัตโนวัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("autopolling", "การหยั่งสัญญาณอัตโนมัติ", null, getIndexByName(computer_Category));
        wordDAO.addWord("auxiliary equipment", "บริภัณฑ์ช่วย", null, getIndexByName(computer_Category));
        wordDAO.addWord("auxiliary storage", "หน่วยเก็บช่วย", null, getIndexByName(computer_Category));
        wordDAO.addWord("availability", "สภาพพร้อมใช้งาน", null, getIndexByName(computer_Category));
        wordDAO.addWord("available machine time; available time", "เวลาเครื่องพร้อม", null, getIndexByName(computer_Category));
        wordDAO.addWord("back-end", "-ส่วนหลัง", null, getIndexByName(computer_Category));
        wordDAO.addWord("backbone", "แกนหลัก", null, getIndexByName(computer_Category));

        /*for (int i = 0; i < 100; i++) {
            wordDAO.addWord("zzz" + i, "แซ่ด", null,
                    getIndexByName(computer_Category));
        }*/
    }

    public void createFavorite() {
        FavoriteDAO favoriteDAO = new FavoriteDAO(mContext);

        favoriteDAO.addFavorite(new Word(1, "A", "เอ", null, null));
        favoriteDAO.addFavorite(new Word(2, "Air", "แอร์", null, null));
        favoriteDAO.addFavorite(new Word(11, "G", "จี", null, null));
        favoriteDAO.addFavorite(new Word(10, "H", "เฮซ", null, null));
    }

    //Getting Index of an item in an arraylist;
    public int getIndexByName(String name) {
        for (String word : mCategory) {
            if (word.equals(name)) {
                return mCategory.indexOf(word);
            }
        }
        return -1;
    }
}
