package cn.edu.mnnu.ams.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.mnnu.ams.dao.IAdminDAO;
import cn.edu.mnnu.ams.dao.IUserDAO;
import cn.edu.mnnu.ams.entity.Admin;
import cn.edu.mnnu.ams.entity.AlumniInfos;
import cn.edu.mnnu.ams.entity.Dept;
import cn.edu.mnnu.ams.entity.ExamineVerify;
import cn.edu.mnnu.ams.entity.User;
import cn.edu.mnnu.ams.model.EchartData;
import cn.edu.mnnu.ams.model.ExamineInfo;

public class AmsService implements IAmsService {

	private IAdminDAO adminDao;
	private IUserDAO userDao;

	public void setAdminDao(IAdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public int Login(String uid, String pwd) {
		Admin au = adminDao.getUser(uid);
		if (au != null) if (au.getPassword().equals(pwd)) return 1;
		User u = userDao.getUser(uid);
		if (u != null) if (u.getPassword().equals(pwd)) return 2;
		return 0;
	}

	@Override
	public Admin getAdminUser(String uid) {
		return adminDao.getUser(uid);
	}

	@Override
	public User getUser(String uid) {
		return userDao.getUser(uid);
	}

	@Override
	public List<AlumniInfos> getAlumniInfo(String condition) {
		if (condition.equals("all")) condition = "1=1";
		else if (condition.lastIndexOf("&") == condition.length() - 1) condition = condition
				.substring(0, condition.length() - 1);
		condition = condition == null ? "1=1" : condition.replace("&", " AND ");
		return adminDao.getAlumniInfos(condition);
	}

	@Override
	public AlumniInfos getAlumniInfoItem(String id) {
		return adminDao.getAlumniInfo(Integer.parseInt(id));
	}

	@Override
	public List<ExamineInfo> getExamineInfo() {
		List<ExamineVerify> list_ev = adminDao.getExamineVerify();
		List<ExamineInfo> list_ei = new ArrayList<ExamineInfo>();
		String[] fileds = { "sno", "dept", "major", "class", "sname", "sex",
				"province_from", "city_from", "district_from", "degree",
				"gra_time", "province_work", "city_work", "district_work",
				"work_unit", "duty", "job", "profession", "phone1", "phone2",
				"mail", "qq", "address", "zip", "note", "assoc1", "assoc1_job",
				"assoc2", "assoc2_job" };
		String[] filedNames = { "学号", "学院", "专业", "班级", "姓名", "性别", "生源地省",
				"生源地市", "生源地县", "学历", "毕业时间", "工作省份", "工作市", "工作地区", "工作单位",
				"职务", "职称", "行业", "联系电话", "固定电话", "邮箱", "QQ", "通讯地址", "邮编",
				"备注", "所属校友总会", "校友总会职务", "所属校友分会", "校友分会职务" };
		for (int i = 0; i < list_ev.size(); i++) {
			ExamineInfo ei = new ExamineInfo(list_ev.get(i));
			String bindid = userDao.getUser(ei.getUserid()).getBindid();
			String oldcontent = adminDao.getFiledInfo(bindid, ei.getFiled());
			ei.setOldcontent(oldcontent);
			for (int j = 0; j < fileds.length; j++) {
				if (ei.getFiled().equals(fileds[j])) {
					ei.setFiled(filedNames[j]);
					break;
				}
			}
			list_ei.add(ei);
		}
		return list_ei;
	}

	@Override
	public List<EchartData> getAreaStatistics(String table) {
		String[] citys = { "未知", "北京市", "天津市", "石家庄市", "唐山市", "秦皇岛市", "邯郸市",
				"邢台市", "保定市", "张家口市", "承德市", "沧州市", "廊坊市", "衡水市", "太原市", "大同市",
				"阳泉市", "长治市", "晋城市", "朔州市", "晋中市", "运城市", "忻州市", "临汾市", "吕梁市",
				"呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市", "呼伦贝尔市", "巴彦淖尔市",
				"乌兰察布市", "兴安盟", "锡林郭勒盟", "阿拉善盟", "沈阳市", "大连市", "鞍山市", "抚顺市",
				"本溪市", "丹东市", "锦州市", "营口市", "阜新市", "辽阳市", "盘锦市", "铁岭市", "朝阳市",
				"葫芦岛市", "长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "白城市",
				"延边朝鲜族自治州", "哈尔滨市", "齐齐哈尔市", "鸡西市", "鹤岗市", "双鸭山市", "大庆市",
				"伊春市", "佳木斯市", "七台河市", "牡丹江市", "黑河市", "绥化市", "大兴安岭地区", "上海市",
				"南京市", "无锡市", "徐州市", "常州市", "苏州市", "南通市", "连云港市", "淮安市", "盐城市",
				"扬州市", "镇江市", "泰州市", "宿迁市", "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市",
				"绍兴市", "金华市", "衢州市", "舟山市", "台州市", "丽水市", "合肥市", "芜湖市", "蚌埠市",
				"淮南市", "马鞍山市", "淮北市", "铜陵市", "安庆市", "黄山市", "滁州市", "阜阳市", "宿州市",
				"巢湖市", "六安市", "亳州市", "池州市", "宣城市", "福州市", "厦门市", "莆田市", "三明市",
				"泉州市", "漳州市", "南平市", "龙岩市", "宁德市", "南昌市", "景德镇市", "萍乡市", "九江市",
				"新余市", "鹰潭市", "赣州市", "吉安市", "宜春市", "抚州市", "上饶市", "济南市", "青岛市",
				"淄博市", "枣庄市", "东营市", "烟台市", "潍坊市", "济宁市", "泰安市", "威海市", "日照市",
				"莱芜市", "临沂市", "德州市", "聊城市", "滨州市", "菏泽市", "郑州市", "开封市", "洛阳市",
				"平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市", "许昌市", "漯河市",
				"三门峡市", "南阳市", "商丘市", "信阳市", "周口市", "驻马店市", "武汉市", "黄石市",
				"十堰市", "宜昌市", "襄樊市", "鄂州市", "荆门市", "孝感市", "荆州市", "黄冈市", "咸宁市",
				"随州市", "恩施土家族苗族自治州", "神农架", "长沙市", "株洲市", "湘潭市", "衡阳市", "邵阳市",
				"岳阳市", "常德市", "张家界市", "益阳市", "郴州市", "永州市", "怀化市", "娄底市",
				"湘西土家族苗族自治州", "广州市", "韶关市", "深圳市", "珠海市", "汕头市", "佛山市", "江门市",
				"湛江市", "茂名市", "肇庆市", "惠州市", "梅州市", "汕尾市", "河源市", "阳江市", "清远市",
				"东莞市", "中山市", "潮州市", "揭阳市", "云浮市", "南宁市", "柳州市", "桂林市", "梧州市",
				"北海市", "防城港市", "钦州市", "贵港市", "玉林市", "百色市", "贺州市", "河池市", "来宾市",
				"崇左市", "海口市", "三亚市", "重庆市", "成都市", "自贡市", "攀枝花市", "泸州市", "德阳市",
				"绵阳市", "广元市", "遂宁市", "内江市", "乐山市", "南充市", "眉山市", "宜宾市", "广安市",
				"达州市", "雅安市", "巴中市", "资阳市", "阿坝藏族羌族自治州", "甘孜藏族自治州", "凉山彝族自治州",
				"贵阳市", "六盘水市", "遵义市", "安顺市", "铜仁地区", "黔西南布依族苗族自治州", "毕节地区",
				"黔东南苗族侗族自治州", "黔南布依族苗族自治州", "昆明市", "曲靖市", "玉溪市", "保山市", "昭通市",
				"丽江市", "思茅市", "临沧市", "楚雄彝族自治州", "红河哈尼族彝族自治州", "文山壮族苗族自治州",
				"西双版纳傣族自治州", "大理白族自治州", "德宏傣族景颇族自治州", "怒江傈僳族自治州", "迪庆藏族自治州",
				"拉萨市", "昌都地区", "山南地区", "日喀则地区", "那曲地区", "阿里地区", "林芝地区", "西安市",
				"铜川市", "宝鸡市", "咸阳市", "渭南市", "延安市", "汉中市", "榆林市", "安康市", "商洛市",
				"兰州市", "嘉峪关市", "金昌市", "白银市", "天水市", "武威市", "张掖市", "平凉市", "酒泉市",
				"庆阳市", "定西市", "陇南市", "临夏回族自治州", "甘南藏族自治州", "西宁市", "海东地区",
				"海北藏族自治州", "黄南藏族自治州", "海南藏族自治州", "果洛藏族自治州", "玉树藏族自治州",
				"海西蒙古族藏族自治州", "银川市", "石嘴山市", "吴忠市", "固原市", "中卫市", "乌鲁木齐市",
				"克拉玛依市", "吐鲁番地区", "哈密地区", "昌吉回族自治州", "博尔塔拉蒙古自治州", "巴音郭楞蒙古自治州",
				"阿克苏地区", "克孜勒苏柯尔克孜自治州", "喀什地区", "和田地区", "伊犁哈萨克自治州", "塔城地区",
				"阿勒泰地区", "石河子市", "阿拉尔市", "图木舒克市", "五家渠市", "香港特别行政区", "澳门特别行政区",
				"台湾省" };
		List<EchartData> clist = new ArrayList<EchartData>();
		for (int i = 0; i < citys.length; i++) {
			clist.add(new EchartData(citys[i], adminDao.getCityStatisticsCount(
					i, table)));
		}
		return clist;
	}

	@Override
	public void passEV(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) continue;
			ExamineVerify ev = adminDao.getExamineVerifyItem(Integer
					.parseInt(arr[i]));
			User u = userDao.getUser(ev.getUserid());
			adminDao.setFiledInfo(u.getBindid(), ev.getFiled(), ev.getContent());
			adminDao.delExamineVerifyItem(Integer.parseInt(arr[i]));
		}
	}

	@Override
	public void refuseEV(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) continue;
			adminDao.delExamineVerifyItem(Integer.parseInt(arr[i]));
		}
	}

	@Override
	public void flushEV(List<ExamineInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOldcontent().equals(list.get(i).getContent())) {
				adminDao.delExamineVerifyItem(list.get(i).getId());
			}
		}
	}

	@Override
	public List<EchartData> getDeptStatistics() {
		List<String> dept_arr = adminDao.getDeptArray();
		List<EchartData> list_e = new ArrayList<EchartData>();
		for (int i = 0; i < dept_arr.size(); i++) {
			list_e.add(new EchartData(dept_arr.get(i), adminDao
					.getDeptCount(dept_arr.get(i))));
		}
		return list_e;
	}

	@Override
	public List<String> getJobType(String dept) {
		return adminDao.getJobArray(dept);
	}

	@Override
	public List<EchartData> getJobStatistics(String dept) {
		List<String> duty_arr = adminDao.getJobArray(dept);
		List<EchartData> list_e = new ArrayList<EchartData>();
		for (int i = 0; i < duty_arr.size(); i++) {
			list_e.add(new EchartData(duty_arr.get(i), adminDao.getJobCount(
					duty_arr.get(i), dept)));
		}
		return list_e;
	}

	@Override
	public List<Dept> getDept() {
		return adminDao.getDept();
	}

	@Override
	public void addDept(String id, String name) {
		adminDao.addDept(id,name);
	}

}
