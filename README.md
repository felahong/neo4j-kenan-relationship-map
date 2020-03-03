# neo4j-kenan-relationship-map
图数据库 - 基于Neo4j 和D3j 的柯南和怪盗基德周边关系动态图谱

# Cypher 建立关系图谱
## 创建节点：标签、节点和属性
```
MERGE (p1:Kenan {name:'柯南', image:'kn.png'})
MERGE (p2:Kenan {name:'毛利兰', image:'mll.png'})
MERGE (p3:Kenan {name:'灰原哀', image:'hya.png'})
MERGE (p4:Kenan {name:'服部平次', image:'fbpc.png'})
MERGE (p5:Kenan {name:'工藤优作', image:'gtyz.png'})
MERGE (p6:Kenan {name:'工藤有希子', image:'gtyxz.png'})
MERGE (p7:Kenan {name:'阿笠博士', image:'albs.png'})
MERGE (p8:Kenan {name:'毛利小五郎', image:'mlxwl.png'})
MERGE (p9:Kenan {name:'铃木园子', image:'lmyz.png'})
MERGE (p10:Kenan {name:'远山和叶', image:'yshy.png'})
MERGE (p11:Kenan {name:'妃英理', image:'fyl.png'})
MERGE (p12:Kenan {name:'怪盗基德', image:'gdjd.png'})
MERGE (p13:Kenan {name:'黑羽盗一', image:'hydy.png'})
MERGE (p14:Kenan {name:'白马探', image:'bmt.png'})
MERGE (p15:Kenan {name:'小泉红子', image:'xhqz.png'})
MERGE (p16:Kenan {name:'中森青子', image:'zsqz.png'})
MERGE (p17:Kenan {name:'中森银三', image:'zsys.png'})
MERGE (p18:Kenan {name:'黑羽夫人', image:'hyfr.png'})
MERGE (p19:Kenan {name:'京极真', image:'jjz.png'})
MERGE (p20:Kenan {name:'赤井秀一', image:'cjxy.png'})
```
## 创建图路径：节点间关系
```
MATCH (a:Kenan {name:"工藤优作"}),(b:Kenan {name:"工藤有希子"}) MERGE(a)-[:夫妻]->(b)
MATCH (a:Kenan {name:"工藤优作"}),(b:Kenan {name:"柯南"}) MERGE(a)-[:父亲]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"工藤优作"}) MERGE(a)-[:儿子]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"工藤有希子"}) MERGE(a)-[:儿子]->(b)
MATCH (a:Kenan {name:"工藤优作"}),(b:Kenan {name:"阿笠博士"}) MERGE(a)-[:朋友]->(b)
MATCH (a:Kenan {name:"阿笠博士"}),(b:Kenan {name:"灰原哀"}) MERGE(a)-[:帮助]->(b)
MATCH (a:Kenan {name:"灰原哀"}),(b:Kenan {name:"阿笠博士"}) MERGE(a)-[:寄居]->(b)
MATCH (a:Kenan {name:"灰原哀"}),(b:Kenan {name:"柯南"}) MERGE(a)-[:同是天涯沦落人]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"毛利兰"}) MERGE(a)-[:寄居]->(b)
MATCH (a:Kenan {name:"毛利兰"}),(b:Kenan {name:"柯南"}) MERGE(a)-[:关爱]->(b)
MATCH (a:Kenan {name:"毛利兰"}),(b:Kenan {name:"毛利小五郎"}) MERGE(a)-[:女儿]->(b)
MATCH (a:Kenan {name:"毛利兰"}),(b:Kenan {name:"妃英理"}) MERGE(a)-[:女儿]->(b)
MATCH (a:Kenan {name:"毛利小五郎"}),(b:Kenan {name:"妃英理"}) MERGE(a)-[:夫妻]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"妃英理"}) MERGE(a)-[:畏惧]->(b)
MATCH (a:Kenan {name:"毛利兰"}),(b:Kenan {name:"铃木园子"}) MERGE(a)-[:闺蜜]->(b)
MATCH (a:Kenan {name:"铃木园子"}),(b:Kenan {name:"京极真"}) MERGE(a)-[:情侣]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"服部平次"}) MERGE(a)-[:好友]->(b)
MATCH (a:Kenan {name:"服部平次"}),(b:Kenan {name:"远山和叶"}) MERGE(a)-[:青梅竹马]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"怪盗基德"}) MERGE(a)-[:亦敌亦友]->(b)
MATCH (a:Kenan {name:"工藤优作"}),(b:Kenan {name:"黑羽盗一"}) MERGE(a)-[:亦敌亦友]->(b)
MATCH (a:Kenan {name:"怪盗基德"}),(b:Kenan {name:"黑羽盗一"}) MERGE(a)-[:儿子]->(b)
MATCH (a:Kenan {name:"黑羽盗一"}),(b:Kenan {name:"黑羽夫人"}) MERGE(a)-[:夫妻]->(b)
MATCH (a:Kenan {name:"中森银三"}),(b:Kenan {name:"怪盗基德"}) MERGE(a)-[:追捕]->(b)
MATCH (a:Kenan {name:"黑羽夫人"}),(b:Kenan {name:"怪盗基德"}) MERGE(a)-[:母亲]->(b)
MATCH (a:Kenan {name:"中森银三"}),(b:Kenan {name:"中森青子"}) MERGE(a)-[:父亲]->(b)
MATCH (a:Kenan {name:"怪盗基德"}),(b:Kenan {name:"中森青子"}) MERGE(a)-[:青梅竹马]->(b)
MATCH (a:Kenan {name:"白马探"}),(b:Kenan {name:"怪盗基德"}) MERGE(a)-[:死对头]->(b)
MATCH (a:Kenan {name:"怪盗基德"}),(b:Kenan {name:"小泉红子"}) MERGE(a)-[:同班同学]->(b)
MATCH (a:Kenan {name:"小泉红子"}),(b:Kenan {name:"中森青子"}) MERGE(a)-[:同班同学]->(b)
MATCH (a:Kenan {name:"赤井秀一"}),(b:Kenan {name:"柯南"}) MERGE(a)-[:寄居]->(b)
MATCH (a:Kenan {name:"柯南"}),(b:Kenan {name:"赤井秀一"}) MERGE(a)-[:合作]->(b)
```







