-- LIBRARY APP TASKS --

-- Display book name and related bookCategory name from library
    select b.name as book_name,c.name as category from books b inner join book_categories c on b.book_category_id = c.id order by category,book_name;
-- Find me how many book we have in each category
   select c.name,count(b.name) Number_Of_Books from books b
    inner join book_categories c on b.book_category_id=c.id
   group by b.book_category_id order by count(b.name) desc;
-- Display most popular book category ( this book needs to borrow to be popular.)

    #List of books name, category and count of how many times borrowed
   select b.name,c.name as category,count(*) as count from book_borrow br
    inner join books b on br.book_id = b.id
        inner join book_categories c on b.book_category_id = c.id
   group by book_id order by count(*) desc;
    #List of categories with how many times a book has been borrowed from that category
        select category, sum(count) as total from (
          select b.name,c.name as category,count(*) as count from book_borrow br
              inner join books b on br.book_id = b.id
              inner join book_categories c on b.book_category_id = c.id
          group by book_id order by count(*) desc) q
        group by category order by sum(count) desc;
   #Answer of the Question. Instead of using rownum which there is no such thing in mySQL, I have used limit
   select q2.category from (
        select category, sum(count) as total from (
            select b.name,c.name as category,count(*) as count from book_borrow br
                inner join books b on br.book_id = b.id
                    inner join book_categories c on b.book_category_id = c.id
            group by book_id order by count(*) desc) q
        group by category order by sum(count) desc) q2 limit 0,1;
-- Display how many books are borrowed not turn back yet
    select count(*) from book_borrow where is_returned=0;