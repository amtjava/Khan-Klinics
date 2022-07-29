export default function handleTree(data, id) {
	id = id || 'id'
  const parentId = 'parentId'
  const children = 'children'
  const rootId = Math.min.apply(Math, data.map(item => { return item[parentId] })) || 0
  //对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data))
  //循环所有项
  const treeData = cloneData.filter(father => {
    let branchArr = cloneData.filter(child => {
      //返回每一项的子级数组
      return father[id] === child[parentId]
    });
    branchArr.length > 0 ? father.children = branchArr : '';
    //返回第一层
    return father[parentId] === rootId;
  });
  return treeData !== '' ? treeData : data;
}