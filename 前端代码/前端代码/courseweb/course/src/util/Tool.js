
export function arrayToTree(array,parentId){
    if(isEmpty(array)){
        return []
    }

    const result = [];
    for(let i = 0; i<array.length; i++){
        const c = array[i];
        if (Number(c.parent) === Number(parentId)){
            result.push(c);

            // 递归
            const children = arrayToTree(array , c.id);
            if (!isEmpty(children)){
                c.children = children;
            }
        }
    }
    return result;
}

export function isEmpty(obj){
    if ((typeof obj === 'string')) {
        return !obj || obj.replace(/\s+/g, "") === ""
    } else {
        return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
}



