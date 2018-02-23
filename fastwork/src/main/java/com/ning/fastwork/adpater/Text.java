package com.ning.fastwork.adpater;

/**
 *  这个 adapter 的用法
 */

public class Text {

    /**
     *          一种 item
     *
     *         recyclerView.setAdapter(new CommonAdapter<String>(context,R.layout.item_repayment_plan,list) {
                     @Override
                 protected void convert(ViewHolder holder, String o, int position) {

                    }

                 });
     * */

    /**
     *           //不同类型 item 的适配器
     *
                MultiItemTypeAdapter adapter = new MultiItemTypeAdapter<String>(this,list);
                adapter.addItemViewDelegate(new SavingsDepositCard());
                adapter.addItemViewDelegate(new CreditCard());

                 recyclerView.setAdapter(adapter);


                对应的 item 布局,
                 private class CreditCard implements ItemViewDelegate<String>
                {

                     @Override
                     public int getItemViewLayoutId()
                     {
                         return R.layout.item_bank_card_manage_2;
                     }

                     @Override
                     public boolean isForViewType(String item, int position)
                     {
                      return "2".equals(item);
                     }

                     @Override
                     public void convert(ViewHolder holder, String chatMessage, int position)
                     {
                        //            holder.setText(R.id.chat_from_content, chatMessage.getContent());
                         //            holder.setText(R.id.chat_from_name, chatMessage.getName());
                         //            holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
                     }
                }
     *
     * */



}
